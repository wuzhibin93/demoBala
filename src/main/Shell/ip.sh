#!/usr/bin/env bash
sed -i "s/#network.host: 192.168.0.1/network.host: 0.0.0.0/g"  /etc/elasticsearch/elasticsearch.yml