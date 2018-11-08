#!/usr/bin/env bash
# Install JDK
echo 'install jdk start ......'
rpm -i jdk-8u172-linux-x64.rpm
echo 'install jdk success!'
echo '====================================================='

# Isntall Elasticsearch
echo 'install elasticsearch start ......'
rpm -i elasticsearch-6.3.0.rpm
echo 'install elasticsearch success!'
echo '====================================================='

# Install Elasticsearch plugins
echo 'install elasticsearch-pluginss[ingest-geoip] start ......'
echo y | /usr/share/elasticsearch/bin/elasticsearch-plugin install file:./ingest-geoip-6.3.0.zip
echo 'install elasticsearch-pluginss[ingest-geoip] success!'
echo '====================================================='

echo 'install elasticsearch-pluginss[ingest-user-agent] start ......'
echo y | /usr/share/elasticsearch/bin/elasticsearch-plugin install file:./ingest-user-agent-6.3.0.zip
echo 'install elasticsearch-pluginss[ingest-user-agent] success!'
echo '====================================================='

sed -i "s/#network.host: 192.168.0.1/network.host: 0.0.0.0/g"  /etc/elasticsearch/elasticsearch.yml