#!/usr/bin/env python
from __future__ import print_function
import pexpect, csv, math, sys, argparse, re, os

# Input Arguments
parser = argparse.ArgumentParser(description='Scrape an LDAP directory')
parser.add_argument('input_file', metavar='fin', type=str, help='Input CSV file containing aliases of uids to scrape')
parser.add_argument('output_file', metavar='fout', type=str, help='Output CSV file for scraped information')
parser.add_argument('-cn', type=str, nargs='+', help='Common name(s)', default=[])
parser.add_argument('-ou', type=str, nargs='+', help='Organizational unit(s)', default=['users','moira'])
parser.add_argument('-dc', type=str, nargs='+', help='Domain component(s)', default=['mit','edu'])
args = parser.parse_args()

# Initialize header fields
fields = [];

# Construct searchbase -b command line input
searchbase = ''
if args.cn:
	for cn in args.cn:
		searchbase += 'cn={},'.format(cn)
if args.ou:
	for ou in args.ou:
		searchbase += 'ou={},'.format(ou)
if args.dc:
	for dc in args.dc:
		searchbase += 'dc={},'.format(dc)
searchbase = searchbase[:-1]

# If the output file already exists, copy it without the fields into a temporary csv
lastUID = ''
if os.path.isfile(args.output_file):
	with open(args.output_file, 'rb') as file:
		with open('temp_'+args.output_file, 'wb') as temp_file:
			reader = csv.reader(file, delimiter=',')
			writer = csv.writer(temp_file)
			found_footer = False
			for row in reader:
				if row[0] == 'uid':
					found_footer = True
					fields = row[1:]
					break
				else:
					writer.writerow(row)
					lastUID = row[0]
			if not found_footer:
				raise Exception('If output file exists, it must conclude with a row of fieldnames.')

	os.remove(args.output_file)
	os.rename('temp_'+args.output_file, args.output_file)

# Scrape LDAP
no_errors = True
with open(args.output_file, 'a+') as writefile:
	writer = csv.writer(writefile)
	with open(args.input_file, 'r') as readfile:
		reader = csv.reader(readfile, delimiter=',')
		r = 0
		pct = 0
		for row in reader:
			num_aliases = sum(1 for alias in row)
			record_data = False if lastUID else True
			for alias in row:
				r+=1
				if record_data:
					try:
						proc = pexpect.spawn(r'ldapsearch -LLL -x -h ldap -b "{0}" "uid"="{1}"'.format(searchbase, alias))
						proc.expect(pexpect.EOF)
						ldap = proc.before.decode('utf-8')
						if "Can't contact" in ldap:
							raise
					except:
						no_errors = False
						break

					attrs = re.findall(r'(.+): (.+)', ldap, re.MULTILINE)
					writeRow = ['']*len(fields)
					for attr in attrs:
						field = attr[0]
						value = attr[1][:-1]
						if field in fields:
							writeRow[fields.index(field)] = value
						else:
							writeRow.append(value)
							fields.append(field)

					writeRow.insert(0,alias)
					writer.writerow(writeRow)

					# Debug Display
					if (100*r/num_aliases>=pct+1):
						pct = math.floor(100*r/num_aliases)
						print(str(pct) + "%", end='\r')
						sys.stdout.flush()
				else:
					record_data = alias == lastUID

	fields.insert(0,'uid')
	writer.writerow(fields)

# Present data
if no_errors:
	with open(args.output_file, 'r') as file:
		reader = csv.reader(file, delimiter=',')
		with open('temp_'+args.output_file, 'w') as temp_file:
			writer = csv.writer(temp_file)
			writer.writerow(fields)
			for row in reader:
				if row[0] != 'uid':
					writer.writerow(row)
	os.remove(args.output_file)
	os.rename('temp_'+args.output_file, args.output_file)
	print('Completed without errors!')
else:
	print('Connection lost! Please reconnect and start again.')