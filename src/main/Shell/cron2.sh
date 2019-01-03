#!/usr/bin/env bash
#echo "Start esrally at :"`date +"%Y-%m-%d %H-%M-%S"` >> /usr/local/esrally.log
#esrally race --pipeline=benchmark-only --target-hosts=127.0.0.1:9200 --track=geonames --challenge=append-no-conflicts
#echo "End esrally at :"`date +"%Y-%m-%d %H-%M-%S"` >> /usr/local/esrally.log

index=twitter-2018.02.21
echo ${index%-*}
curl -H "Content-Type:application/json" -XPOST '192.168.100.51:9200/_reindex?pretty=true' -d'
        {
        "source": {
            "index": "'${index%-*}'"
            },
        "dest": {
            "index": "'${index}'-backup"
            }
        }
        '
#curl -X POST "192.168.100.51:9200/_reindex?pretty=true" -H 'Content-Type: application/json' -d'
#{
#  "source": {
#    "index": "'${index%-*}'"
#  },
#  "dest": {
#    "index": "'${index%-*}'-backup"
#  }
#}
#'


echo "-----------ss-------------"