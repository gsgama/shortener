#!/usr/bin/env python
#-*- coding: utf-8 -*- 

import httplib
import json

HOST = 'localhost:8080'
SHORTENER_ENDPOINT = '/'
API_ENDPOINT = '/api'

def do_request(method, url):
	conn = httplib.HTTPConnection(HOST)
	conn.request(method, url);
	resp = conn.getresponse()
	data = resp.read()
	conn.close()

	return json.loads(data)

def most_accessed():
	results = do_request("GET", API_ENDPOINT + "/most-viewed")
	for url in results:
		print unicode(url['accesses']) + " " + url['url']

def shorten_url(url, alias=None):
	url = SHORTENER_ENDPOINT + "/create?url=" + url
	if alias:
		url += "&CUSTOM_ALIAS="	+ alias
	result = do_request("PUT", url)
	print "http://" + HOST + "/" + result['alias']

def expand_url(alias):
	result = do_request("GET", API_ENDPOINT + "/filter?alias=" + alias)
	print result['url']

def main():
	import argparse

	parser = argparse.ArgumentParser()
	parser.add_argument("-u", "--url", help="Enter a url to shorten")
	parser.add_argument("-a", "--alias", help="Enter an alias to expand")
	parser.add_argument("-m", "--most_accessed", action="store_true", help="Shows most accessed URL'd")
	args = parser.parse_args()

	if args.most_accessed:
		most_accessed()
	elif args.url and args.alias:
		shorten_url(args.url, args.alias)
	elif args.alias:
		expand_url(args.alias)
	elif args.url:
		shorten_url(args.url)
	else:
		parser.print_help()

if __name__ == '__main__':
	main()

