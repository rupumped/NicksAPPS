#! /usr/bin/env python

import pexpect, csv, argparse, re, math

EMAIL_PATTERN = re.compile(r'(.+)@.*mit\.edu')

parser = argparse.ArgumentParser(description='Check Slack membership against an LDAP database.')
parser.add_argument('input_file', metavar='fin', type=str, help='Input CSV file downloaded from Slack workspace containing list of members')
parser.add_argument('-cn', type=str, nargs='+', help='Common name(s)', default=[])
parser.add_argument('-ou', type=str, nargs='+', help='Organizational unit(s)', default=['users','moira'])
parser.add_argument('-dc', type=str, nargs='+', help='Domain component(s)', default=['mit','edu'])
parser.add_argument('-crit', type=str, nargs=1, help='String that must be contained in LDAP result', default='mitDirStudentYear: G')
args = parser.parse_args()

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

with open(args.input_file) as members_file:
	reader = csv.DictReader(members_file)
	num_users = len(list(reader))

bad_users = []
with open(args.input_file) as members_file:
	reader = csv.DictReader(members_file)
	r, pct = 0, 0
	for row in reader:
		r+= 1
		if row['status']!='Primary Owner' and row['status']!='Admin' and row['status']!='Bot' and row['status']!='Deactivated':
			email = row['email']
			m = EMAIL_PATTERN.findall(email)
			if len(m)>0:
				alias = m[0]
				proc = pexpect.spawn(r'ldapsearch -LLL -x -h ldap -b "{0}" "uid"="{1}"'.format(searchbase, alias))
				proc.expect(pexpect.EOF)
				ldap = str(proc.before)
				assert (not ("Can't contact" in ldap)), 'Failure to connect to LDAP. Please check your internet connection.'
				if not (args.crit in ldap):
					bad_users.append(row)
			else:
				bad_users.append(row)

		# Debug Display
		if (100*r/num_users>=pct+1):
			pct = math.floor(100*r/num_users)
			print(str(pct) + "%", end='\r')

print('The following members may not be current graduate students:')
for user in bad_users:
	print('Name: {0}, Email: {1}, Active: {2}'.format(user['fullname'], user['email'], user['billing-active']))