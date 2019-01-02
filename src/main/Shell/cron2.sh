#!/usr/bin/env bash
#echo "Start esrally at :"`date +"%Y-%m-%d %H-%M-%S"` >> /usr/local/esrally.log
#esrally race --pipeline=benchmark-only --target-hosts=127.0.0.1:9200 --track=geonames --challenge=append-no-conflicts
#echo "End esrally at :"`date +"%Y-%m-%d %H-%M-%S"` >> /usr/local/esrally.log

index=twitter-2018.02.21
echo ${index%-*}

echo "------------------------"