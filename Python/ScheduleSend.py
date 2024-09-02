import http.server, socketserver, urllib.parse, webbrowser, requests, re
from datetime import datetime
import argparse
from tqdm import tqdm

# Input Arguments
parser = argparse.ArgumentParser(description='Merge one Microsoft Teams channel into another')
parser.add_argument('tenant_id', metavar='tid', type=str, help='Microsoft Entra Tenant ID, available in the Identity/Overview tab of the Microsoft Entra admin center')
parser.add_argument('client_id', metavar='cid', type=str, help='Microsoft Entra Application (Client) ID, available in the Identity/Applications/App registrations/Overview tab of the Microsoft Entra admin center')
parser.add_argument('client_secret', metavar='cs', type=str, help='Microsoft Entra Client secret, available in the Identity/Applications/App registrations/Certifications & secrets tab of the Microsoft Entra admin center')
parser.add_argument('channel', type=str, help='The URL of the channel in which to post')
parser.add_argument('-redirect_uri', type=str, help='Server redirect URI to receive your authorization code from Microsoft', default='http://localhost:8000/callback')
parser.add_argument('-scope', type=str, help='Microsoft Graph API scope', default='https://graph.microsoft.com/.default')
parser.add_argument('-v', '--verbose', action='store_true', help='Verbose mode')
args = parser.parse_args()

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
	# Construct message
	postedDate = datetime.strptime(message['createdDateTime'], '%Y-%m-%dT%H:%M:%S.%fZ').strftime('%B %d, %Y %H:%M:%S')
	user = message['from']['user']['displayName']
	if message['body']['contentType'] == 'text':
		message['body']['contentType'] = 'html'
		message['body']['content'] = '<p>' + message['body']['content'] + '</p>'
	message['body']['content'] = f'<p><b>{user} posted on {postedDate}</b></p>' + message['body']['content']
	attachments = message['attachments']
	if attachments:
		message['body']['content']+= '<br><p><b>Attachments:</b><p><ul>'
		for attachment in attachments:
			message['body']['content']+= '<li><a target="_blank" href="{}">{}</a></li>'.format(attachment['contentUrl'], attachment['name'])
		message['body']['content']+= '</ul>'
	# TODO: deal with inline attachments, like screenshots included in the body of a message

	# Post each message to the destination channel
	post_data = {
		'body': message['body']
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
	else:
		print(f'Failed to post message: {post_response.status_code} - {response.text}')
		exit(0)

	return post_response.json()['id']

def getTeamAndChannelID(url):
	return (
		re.search(r'groupId=(.+?)&', url).group(1),
		re.search(r'channel/(.+?)/', url).group(1).replace('%3A',':').replace('%40','@')
	)

# Function to fetch replies for a given message
def fetchReplies(message_id, source_team_id, source_channel_id, headers):
	response = requests.get(
		f'https://graph.microsoft.com/v1.0/teams/{source_team_id}/channels/{source_channel_id}/messages/{message_id}/replies',
		headers=headers
	)
	if response.status_code == 200:
		replies = response.json().get('value', [])
		if replies:
			replies.reverse()
		return replies
	else:
		print(f'Failed to retrieve replies: {response.status_code} - {response.text}')
		exit(0)

headers = getAccessHeader(args.tenant_id, args.client_id, args.client_secret, args.redirect_uri, args.scope)

team_id, channel_id = getTeamAndChannelID(args.channel)

# Get messages from the source channel
response = requests.get(
	f'https://graph.microsoft.com/v1.0/teams/{team_id}/channels/{channel_id}/messages',
	headers=headers
)

if response.status_code == 200:
	threads = response.json().get('value', [])
	threads.reverse()
	for titleMessage in tqdm(threads):
		# Send message
		newID = sendMessage(titleMessage, destination_team_id, destination_channel_id, headers, None, titleMessage['subject'] if titleMessage['subject'] else None)

		if newID:
			# Get replies
			replies = fetchReplies(titleMessage['id'], source_team_id, source_channel_id, headers)

			# Post replies
			for reply in replies:
				sendMessage(reply, destination_team_id, destination_channel_id, headers, replyToId=newID)

else:
	print(f'Failed to get messages: {response.status_code} - {response.text}')