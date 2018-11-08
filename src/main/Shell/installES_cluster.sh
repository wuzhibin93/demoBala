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

#数组长度 ${#array[*]}或者${#array[@]}
#参数内容 ${array[*]}或者${array[@]}
clustername="cluster.name: \"$1\""
hosts="discovery.zen.ping.unicast.hosts:"
masterhost="discovery.zen.minimum_master_nodes:"
ips=""
echo $hosts
#接受传入数据
ip_array=($@)
echo "${#ip_array[@]}"
echo "$@"
for ip in ${ip_array[*]}
do
	ips="${ips}""\""${ip}\""",""
done
ips=${ips#*,}

# Config Elasticsearch
echo 'config elasticsearch start ......'
echo "${clustername}" >> /etc/elasticsearch/elasticsearch.yml
echo 'node.name: '${HOSTNAME} >> /etc/elasticsearch/elasticsearch.yml
echo 'network.host: 0.0.0.0' >> /etc/elasticsearch/elasticsearch.yml
echo "${hosts}"" ""[${ips%*,}]" >> /etc/elasticsearch/elasticsearch.yml
echo "${masterhost}"" "`expr ${#ip_array[*]} / 2  + 1` >> /etc/elasticsearch/elasticsearch.yml
echo 'bootstrap.memory_lock: false' >> /etc/elasticsearch/elasticsearch.yml
echo 'bootstrap.system_call_filter: false' >> /etc/elasticsearch/elasticsearch.yml
echo 'config elasticsearch success!'
echo '====================================================='
