#! /usr/bin/env python

# Imports
from apiclient import discovery, errors
from httplib2 import Http
from oauth2client import file, client, tools
import argparse

# Parse Terminal Input
parser = argparse.ArgumentParser(description='List all documents in GDrive with link sharing.')
parser.add_argument('--d', metavar='DIR', type=str, help='Name of the folder within which to search. Use "/" to search within subfolders.')
parser.add_argument('--cred', metavar='CREDENTIALS_FILE', type=str, default='client_id.json', help='Name of json file containing OAuth credentials')
args = parser.parse_args()

# Establish Connection
SCOPES = 'https://www.googleapis.com/auth/drive.readonly.metadata'
store = file.Storage('storage.json')
creds = store.get()
if not creds or creds.invalid:
    flow = client.flow_from_clientsecrets(args.cred, SCOPES)
    creds = tools.run_flow(flow, store)
DRIVE = discovery.build('drive', 'v3', http=creds.authorize(Http()))
files = DRIVE.files().list().execute().get('files', [])

# Recursively Search GDrive for Documents with Link Sharing
def print_files_in_folder(service, folderId):
	request = service.files().list(q="'" + folderId + "' in parents",fields="files(id,name,mimeType,permissions)")
	while request is not None:
		response = request.execute()
		for f in response['files']:
			if 'permissions' in f:
				permissions = f['permissions']
				for p in permissions:
					if p['id']=='anyoneWithLink':
						print(f['name'])
						break
			else:
				print('*' + f['name'])

			if f['mimeType']=='application/vnd.google-apps.folder':
				print_files_in_folder(service, f['id'])
			
		request = service.files().list_next(request, response)

# Get Folder ID
folderId = 'root'
if args.d is not None:
	folderName = args.d.split('/')
	for fn in folderName:
		files = DRIVE.files().list(q="'" + folderId + "' in parents").execute()['files']
		lastFolderId = folderId
		for f in files:
			if f['name']==fn and f['mimeType']=='application/vnd.google-apps.folder':
				folderId = f['id']
				break
		if folderId==lastFolderId:
			print('Invalid directory: ' + fn + ' cannot be found in path.')
			exit()

print_files_in_folder(DRIVE, folderId)