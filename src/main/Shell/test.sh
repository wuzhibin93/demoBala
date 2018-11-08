#!/usr/bin/env bash
#参数数量 $#
#参数内容 $*
ip_array=($*)
echo ${ip_array[@]}
echo "discovery.zen.ping.unicast.hosts: ${ip_array[@]}" >> /usr/local/elasticsearch-6.3.0/config/elasticsearch.yml