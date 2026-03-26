import http.server, socketserver, urllib.parse, webbrowser, requests, re
from datetime import datetime
import argparse
import json
from tqdm import tqdm
import time
import os
import pickle

# Input Arguments
parser = argparse.ArgumentParser(description='Load messages from a JSON file into a Microsoft Teams channel')
parser.add_argument('tenant_id', metavar='tid', type=str, help='Microsoft Entra Tenant ID, available in the Identity/Overview tab of the Microsoft Entra admin center')
parser.add_argument('client_id', metavar='cid', type=str, help='Microsoft Entra Application (Client) ID, available in the Identity/Applications/App registrations/Overview tab of the Microsoft Entra admin center')
parser.add_argument('client_secret', metavar='cs', type=str, help='Microsoft Entra Client secret, available in the Identity/Applications/App registrations/Certifications & secrets tab of the Microsoft Entra admin center')
parser.add_argument('json_file', metavar='json', type=str, help='Path to the JSON file containing messages to load')
parser.add_argument('dest_channel', metavar='dest', type=str, help='The URL of the channel to which you want to load messages')
parser.add_argument('-redirect_uri', type=str, help='Server redirect URI to receive your authorization code from Microsoft', default='http://localhost:8000/callback')
parser.add_argument('-scope', type=str, help='Microsoft Graph API scope', default='https://graph.microsoft.com/.default')
parser.add_argument('-v', '--verbose', action='store_true', help='Verbose mode')
parser.add_argument('-resume', action='store_true', help='Resume from previous progress file')
args = parser.parse_args()

SKIP_CONTENT = '<systemEventMessage/>'

def getAccessHeader(tenant_id, client_id, client_secret, redirect_uri, scope):
	# Authorization URL
	authorization_url = (
		f'https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/authorize'
		f'?client_id={client_id}'
		f'&response_type=code'
		f'&redirect_uri={urllib.parse.quote(redirect_uri)}'
		f'&response_mode=query'
		f'&scope={urllib.parse.quote(scope)}'
	)

	# Open the authorization URL in the user's browser
	webbrowser.open(authorization_url)

	# Handler to capture the authorization code
	class MyHttpRequestHandler(http.server.SimpleHTTPRequestHandler):
		def do_GET(self):
			if self.path.startswith('/callback'):
				query = urllib.parse.urlparse(self.path).query
				params = urllib.parse.parse_qs(query)
				self.send_response(200)
				self.send_header('Content-type', 'text/html')
				self.end_headers()
				self.wfile.write(b"Authorization code received. You can close this window.")
				authorization_code = params['code'][0]
				self.server.authorization_code = authorization_code
			else:
				self.send_response(404)
				self.end_headers()

	# Run the local server
	PORT = 8000
	# Allow port reuse to avoid "Address already in use" error
	socketserver.TCPServer.allow_reuse_address = True
	with socketserver.TCPServer(("", PORT), MyHttpRequestHandler) as httpd:
		if args.verbose:
			print(f"Serving at port {PORT}")
		httpd.handle_request()
		authorization_code = httpd.authorization_code

	# Exchange the authorization code for an access token
	token_url = f'https://login.microsoftonline.com/{tenant_id}/oauth2/v2.0/token'
	payload = {
		'grant_type': 'authorization_code',
		'client_id': client_id,
		'client_secret': client_secret,
		'code': authorization_code,
		'redirect_uri': redirect_uri,
		'scope': scope
	}

	response = requests.post(token_url, data=payload)
	token_data = response.json()

	# Extract the access token
	access_token = token_data.get('access_token')

	if access_token:
		if args.verbose:
			print('Access token obtained successfully')
	else:
		print(f'Failed to obtain access token: {token_data}')
		exit(0)

	# Headers for authorization
	return {
		'Authorization': f'Bearer {access_token}',
		'Content-Type': 'application/json'
	}

def sendMessage(message, destination_team_id, destination_channel_id, headers, replyToId=None, subject=None):
	if message['deletedDateTime'] or message['body']['content']==SKIP_CONTENT:
		return None

	# Make a copy of the message to avoid modifying the original
	message_copy = message.copy()
	message_copy['body'] = message['body'].copy()

	# Check if body already has the preamble
	content = message_copy['body']['content']
	preamble_pattern = r'^<p><b>.+ posted on .+</b></p>'
	has_preamble = re.match(preamble_pattern, content)

	if not has_preamble:
		# Add the preamble
		postedDate = datetime.strptime(message_copy['createdDateTime'], '%Y-%m-%dT%H:%M:%S.%fZ').strftime('%B %d, %Y %H:%M:%S')
		user = message_copy['from']['user']['displayName']
		if message_copy['body']['contentType'] == 'text':
			message_copy['body']['contentType'] = 'html'
			message_copy['body']['content'] = '<p>' + message_copy['body']['content'] + '</p>'
		message_copy['body']['content'] = f'<p><b>{user} posted on {postedDate}</b></p>\n' + message_copy['body']['content']

	# Handle attachments
	attachments = message_copy['attachments']
	if attachments:
		message_copy['body']['content']+= '<br><p><b>Attachments:</b><p><ul>'
		for attachment in attachments:
			message_copy['body']['content']+= '<li><a target="_blank" href="{}">{}</a></li>'.format(attachment['contentUrl'], attachment['name'])
		message_copy['body']['content']+= '</ul>'

	# Post each message to the destination channel
	post_data = {
		'body': message_copy['body']
	}
	if replyToId:
		post_data['replyToId'] = replyToId
	if subject:
		post_data['subject'] = subject

	# Construct POST request URL
	url = f'https://graph.microsoft.com/v1.0/teams/{destination_team_id}/channels/{destination_channel_id}/messages'
	if replyToId:
		url+= '/' + replyToId + '/replies'

	# Send POST request
	post_response = requests.post(
		url,
		headers=headers,
		json=post_data
	)

	if post_response.status_code == 201:
		if args.verbose:
			print('Message posted successfully')
	elif post_response.status_code == 429:
		# Rate limit - raise exception to be caught by resume logic
		raise requests.exceptions.HTTPError(f'429 TooManyRequests: {post_response.text}')
	else:
		print(f'Failed to post message: {post_response.status_code} - {post_response.text}')
		exit(0)

	return post_response.json()['id']

def getTeamAndChannelID(url):
	return (
		re.search(r'groupId=(.+?)&', url).group(1),
		re.search(r'channel/(.+?)/', url).group(1).replace('%3A',':').replace('%40','@')
	)

# Get access headers
headers = getAccessHeader(args.tenant_id, args.client_id, args.client_secret, args.redirect_uri, args.scope)

# Get destination team and channel IDs
destination_team_id, destination_channel_id = getTeamAndChannelID(args.dest_channel)

# Load messages from JSON file
with open(args.json_file, 'r') as f:
	messages = json.load(f)

# Progress file to track completed messages and replies
progress_file = args.json_file + '.progress'

# Load progress if resuming
# Format: {
#   'main_messages': set(),           # Old message IDs that have been posted
#   'replies': {old_msg_id: set()},   # Old reply IDs that have been posted per main message
#   'id_mapping': {old_id: new_id}    # Mapping from old message IDs to new Teams message IDs
# }
progress_data = {'main_messages': set(), 'replies': {}, 'id_mapping': {}}
if args.resume and os.path.exists(progress_file):
	with open(progress_file, 'rb') as f:
		progress_data = pickle.load(f)
	# Handle old progress files that don't have id_mapping
	if 'id_mapping' not in progress_data:
		progress_data['id_mapping'] = {}
	total_processed = len(progress_data['main_messages']) + sum(len(r) for r in progress_data['replies'].values())
	print(f"Resuming: {total_processed} messages/replies already processed")

# Process each message
for message in tqdm(messages):
	message_id = message['id']

	# Extract replies before sending the main message
	replies = message.pop('replies', [])

	# Check if main message already processed
	main_msg_already_posted = message_id in progress_data['main_messages']

	# Send the main message if not already posted
	try:
		if not main_msg_already_posted:
			newID = sendMessage(message, destination_team_id, destination_channel_id, headers, None, message.get('subject'))
			progress_data['main_messages'].add(message_id)
			# Store the mapping from old ID to new ID
			progress_data['id_mapping'][message_id] = newID
			# Initialize reply tracking for this message
			if message_id not in progress_data['replies']:
				progress_data['replies'][message_id] = set()
			# Save progress after main message
			with open(progress_file, 'wb') as f:
				pickle.dump(progress_data, f)
		else:
			# Main message already exists, get the new message ID from our mapping
			newID = progress_data['id_mapping'].get(message_id)
			if not newID:
				if args.verbose:
					print(f"Warning: No ID mapping found for already-posted message {message_id}, skipping remaining replies")
				continue

		# Send replies
		if replies:
			# Get set of already-posted reply IDs for this main message
			posted_reply_ids = progress_data['replies'].get(message_id, set())

			for reply in replies:
				reply_id = reply['id']

				# Skip if this reply was already posted
				if reply_id in posted_reply_ids:
					continue

				# Post the reply using the new message ID
				sendMessage(reply, destination_team_id, destination_channel_id, headers, replyToId=newID)

				# Mark reply as processed
				if message_id not in progress_data['replies']:
					progress_data['replies'][message_id] = set()
				progress_data['replies'][message_id].add(reply_id)

				# Save progress after each reply
				with open(progress_file, 'wb') as f:
					pickle.dump(progress_data, f)

	except requests.exceptions.HTTPError as e:
		if '429' in str(e) or 'TooManyRequests' in str(e):
			print(f"\nRate limit hit. Progress saved to {progress_file}")
			print(f"Run again with -resume flag to continue")
			exit(1)
		else:
			raise

print("All messages loaded successfully!")
print(f"Progress file: {progress_file}")
