# NicksAPPS

## Python
### DrivePermissionCheck
This script checks your Google Drive and reports any files that have link sharing turned on.

Install dependencies using pip:
```
$ pip install --upgrade --user google-api-python-client google-auth-httplib2 google-auth-oauthlib oauth2client
```

Download credentials from https://console.developers.google.com/apis/. Add `client_id.json` and `storage.json` to the `Python` directory.

Run the script in terminal:
```
$ python DrivePermissionCheck.py
```

Or, to search a specific directory, use the `--d` argument.
```
$ python DrivePermissionCheck.py --d path/to/folder
```

### LDAPScrape
Scrapes an LDAP directory given a list of user ID's. Returns database as a CSV.

Install dependencies using pip:
```
$ pip install --upgrade --user python-pexpect ldap-utils argparse
```

For example, if you want to collect the users whose ID's were contained in `input.csv`:
```
nselby,gpburdell,tbeaver
```
from an LDAP database using the query
```
("CN=Dev-India,OU=Distribution,DC=gp,DC=gl,DC=google,DC=com")
```
and store the output in `output.csv`, then you would enter the command:
```
python LDAPScrape.py input.csv output.csv -cn DEV-India -ou Distribution -dc gp gl google com
```
