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