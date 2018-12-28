#!/usr/bin/env bash
curl -H "Content-Type:application/json" -XPOST 'localhost:9200/_reindex?pretty=true' -d'
{
    "source": {
    "index": "twitter",
    "query": {
      "match": {
        "@timestamp": "2018-12-27T10:54:23.000Z"
      }
    }
  },
  "dest": {
    "index": "twitter2"
  }
}
'