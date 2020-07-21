#! /usr/bin/env python

# Imports
from apiclient import discovery, errors
from httplib2 import Http
from oauth2client import file, client, tools
from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import argparse, os, pickle

# Parse Terminal Input
parser = argparse.ArgumentParser(description='List all documents in GDrive with link sharing.')
parser.add_argument('--d', metavar='DIR', type=str, help='Name of the folder within which to search. Use "/" to search within subfolders.')
parser.add_argument('--cred', metavar='CREDENTIALS_FILE', type=str, default='credentials.json', help='Name of json file containing OAuth credentials')
parser.add_argument('--f', action='store_true', help='Forcefully remove link sharing')
parser.add_argument('--verbose', action='store_true', help='If in force mode, notifies user of ')
args = parser.parse_args()

# If modifying these scopes, delete the file token.pickle.
SCOPES = 'https://www.googleapis.com/auth/drive'

creds = None
# The file token.pickle stores the user's access and refresh tokens, and is
# created automatically when the authorization flow completes for the first
# time.
if os.path.exists('token.pickle'):
	with open('token.pickle', 'rb') as token:
		creds = pickle.load(token)
# If there are no (valid) credentials available, let the user log in.
if not creds or not creds.valid:
	if creds and creds.expired and creds.refresh_token:
		creds.refresh(Request())
	else:
		flow = InstalledAppFlow.from_client_secrets_file(
			'credentials.json', SCOPES)
		creds = flow.run_local_server(port=0)
	# Save the credentials for the next run
	with open('token.pickle', 'wb') as token:
		pickle.dump(creds, token)
DRIVE = build('drive', 'v3', credentials=creds)

files = DRIVE.files().list().execute().get('files', [])

# Recursively Search GDrive for Documents with Link Sharing
def print_files_in_folder(service, folderId):
	request = service.files().list(q="'" + folderId + "' in parents",fields="files(id,name,mimeType,permissions)")
	while request is not None:
		response = request.execute()
		for f in response['files']:                                 # For each file in folder
			if 'permissions' in f:                                  # If I have permission to view the permissions of that file
				permissions = f['permissions']
				for p in permissions:                               # For each account with which the file has been shared
					if p['id']=='anyoneWithLink':                   # Check to see if the file has link sharing turned on
						if args.verbose or not args.f:              # If I'm verbose or not forcibly changing the permission, print the name of the file
							print(f['name'])
						if args.f:                                  # If I am forcibly changing the permission, try to delete the link sharing permission
							try:
								service.permissions().delete(fileId=f['id'], permissionId=p['id']).execute()
							except errors.HttpError:
								print('Could not fix permission for ' + f['name'])
						break
			else:                                                   # If I don't have permission, print the name of the file with an asterisk.
				print('*' + f['name'])

			if f['mimeType']=='application/vnd.google-apps.folder': # Recursively process folders
				print_files_in_folder(service, f['id'])
			
		request = service.files().list_next(request, response)      # Iterate

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