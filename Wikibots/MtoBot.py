import pywikibot
from pywikibot import pagegenerators
import requests
import re
import sys
import time
import logging
from tqdm.notebook import tqdm

SPARQL_URL = "https://query.wikidata.org/sparql"
QUERY = """
SELECT ?item ?swLabel WHERE {
  ?item wdt:P17 wd:Q114 .
  ?item rdfs:label ?swLabel .
  FILTER(LANG(?swLabel) = "sw")
  FILTER(STRSTARTS(?swLabel, "Mto "))
  FILTER NOT EXISTS {
	?item rdfs:label ?enLabel .
	FILTER(LANG(?enLabel) = "en") .
	?item schema:description ?enDesc .
	FILTER(LANG(?enDesc) = "en")
  }
}
"""
TEST = False

def get_items():
	"""Fetch matching items from Wikidata SPARQL endpoint."""
	resp = requests.get(SPARQL_URL, params={"query": QUERY, "format": "json"},
						headers={"User-Agent": "MyRiverBot/1.0"})
	resp.raise_for_status()
	results = resp.json()["results"]["bindings"]
	items = []
	for r in results:
		qid = r["item"]["value"].split("/")[-1]   # e.g. "Q12345"
		sw_label = r["swLabel"]["value"]           # e.g. "Mto Tana"
		river_name = sw_label.removeprefix("Mto ").strip()
		if river_name.endswith(")") and "(" in river_name:
			base, paren = river_name.rsplit("(", 1)
			en_label = f"{base.strip()} River ({paren}"
		else:
			en_label = f"{river_name} River"
		items.append((qid, en_label))
	return items

def main():
	site = pywikibot.Site("wikidata", "wikidata")
	repo = site.data_repository()
	logging.getLogger('pywikibot').setLevel(logging.ERROR)
	_real_stderr = sys.stderr
	class _NoSleep:
		def write(self, s):
			if 'Sleeping for' not in s:
				_real_stderr.write(s)
		def flush(self):
			_real_stderr.flush()
		def __getattr__(self, name):
			return getattr(_real_stderr, name)
	sys.stderr = _NoSleep()

	items = get_items()
	print(f"Found {len(items)} items to update.")

	for qid, en_label in tqdm(items, desc="Updating items"):
		item = pywikibot.ItemPage(repo, qid)
		item.get()

		has_en_label = "en" in item.labels
		has_en_desc = "en" in item.descriptions

		# Skip only if both already exist
		if has_en_label and has_en_desc:
			tqdm.write(f"Skipping {qid} — English label and description already exist.")
			continue

		tqdm.write(f"{'Testing ' if TEST else ''}Updating {qid}: label='{en_label}'")

		if not TEST:
			if not has_en_label:
				item.editLabels(
					{"en": en_label},
					summary="Adding English label derived from Swahili 'Mto X' label"
				)
			if not has_en_desc:
				try:
					item.editDescriptions(
						{"en": "river in Kenya"},
						summary="Adding English description for river in Kenya"
					)
				except pywikibot.exceptions.OtherPageSaveError as e:
					if "modification-failed" in str(e):
						match = re.search(r'modification-failed: Item \[\[(Q\d+)|Q\d+\]\] already has', str(e))
						conflicting = match.group(1) if match else "unknown"
						tqdm.write(f"Label+description conflict: {qid} conflicts with {conflicting} — manual merge needed.")
						tqdm.write(f"  https://www.wikidata.org/wiki/{qid}")
						tqdm.write(f"  https://www.wikidata.org/wiki/{conflicting}")
					else:
						raise

if __name__ == "__main__":
	main()