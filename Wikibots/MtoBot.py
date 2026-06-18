import pywikibot
from pywikibot import pagegenerators
import requests
import time

SPARQL_URL = "https://query.wikidata.org/sparql"
QUERY = """
SELECT ?item ?swLabel WHERE {
  ?item wdt:P17 wd:Q114 .
  ?item rdfs:label ?swLabel .
  FILTER(LANG(?swLabel) = "sw")
  FILTER(STRSTARTS(?swLabel, "Mto "))
  FILTER NOT EXISTS {
	?item rdfs:label ?enLabel .
	FILTER(LANG(?enLabel) = "en")
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

	items = get_items()
	print(f"Found {len(items)} items to update.")

	for qid, en_label in items:
		item = pywikibot.ItemPage(repo, qid)
		item.get()

		# Safety check: skip if an English label was added since our query
		if "en" in item.labels:
			print(f"Skipping {qid} — English label already exists.")
			continue

		print(f"{'Testing ' if TEST else ''}Updating {qid}: label='{en_label}'")

		if not TEST:
			item.editLabels(
				{"en": en_label},
				summary="Adding English label derived from Swahili 'Mto X' label"
			)
			item.editDescriptions(
				{"en": "river in Kenya"},
				summary="Adding English description for river in Kenya"
			)

		time.sleep(1)  # Be polite to the API

		exit(0)

if __name__ == "__main__":
	main()