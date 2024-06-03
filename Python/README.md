# NicksAPPS

## Python
### DrivePermissionCheck
This script checks your Google Drive and reports any files that have link sharing turned on.

Install dependencies using pip:
```
$ pip install --upgrade --user google-api-python-client google-auth-httplib2 google-auth-oauthlib oauth2client
```

Enable the Drive API here: https://developers.google.com/drive/api/v3/quickstart/python. Download your `credentials.json` file to the current Python directory.

Run the script in terminal:
```
$ python DrivePermissionCheck.py
```

Or, to search a specific directory, use the `--d` argument.
```
$ python DrivePermissionCheck.py --d path/to/folder
```
If you want the script to automatically disable link sharing for all files, enable the `--f` flag.

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

### MergeTeamsChannels
Copies all messages from one Microsoft Teams channel into another.

Install dependencies using pip:
```
pip install argparse tqdm
```

Example usage:
```
python MergeTeamsChannels.py 3141592-2bf0-4c6a-ab62-e778a663d4fa fc3141592-8b65-4d16-9045-fd451d78c3b3 8Ua8Q~Zjdwq3141592KEYJSSE1p-_m_IsTM68S_c_1 "https://teams.microsoft.com/l/channel/19%3A83f731415927acb93023603bbe%40thread.tacv2/Src?groupId=3141592-b478-4a89-b532-0e8bd651c3fa&tenantId=473bd761-2bf0-4c6a-ab62-e778a663d4fa" "https://teams.microsoft.com/l/channel/19%3Aa2eacca152c24e43141592ca820471%40thread.tacv2/Dest?groupId=3141592-b478-4a89-b532-0e8bd651c3fa&tenantId=473bd761-2bf0-4c6a-ab62-e778a663d4fa"
```

The script will launch a new browser window through which you'll have to log into a Microsoft account with access to both the source and destination Teams channels. If the browser fails to load the window, try refreshing a few times.

### Txt2HTML
Converts a Google Doc into an HTML blog post.

Download your Google Doc as a `.txt` file. Then run this script on the downloaded file:
```
$ python txt2html input.txt
```
where `input.txt` is the name of the downloaded Google Doc. The script will create a file `blogpost.html` containing an HTML-formatted verstion of `input.txt`.

### SlackMembersLDAPCheck
Check Slack membership against an LDAP database.

Install dependencies using pip:
```
$ pip install --upgrade --user python-pexpect ldap-utils argparse
```

Export and download the workspace's member list as a CSV from the admin panel. For example, if the file name is `slack-workspace-members.csv`, and the LDAP query was
```
("CN=Dev-India,OU=Distribution,DC=gp,DC=gl,DC=google,DC=com")
```
and you wanted to check that every email in the workspace corresponded to an alias containing the following LDAP information:
```
mitDirStudentYear: G
```
then you would enter the command:
```
python SlackMembersLDAPCheck.py slack-workspace-members.csv -cn DEV-India -ou Distribution -dc gp gl google com -crit mitDirStudentYear:\ G
```