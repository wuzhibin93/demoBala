#!/usr/bin/env bash
#groupadd and useradd
#groupadd es
#useradd es -g es -p 111111
# Config limints.conf
echo '====/etc/security/limits.conf===='
echo 'es hard nofile 65536' >> /etc/security/limits.conf
echo 'es soft nofile 65536' >> /etc/security/limits.conf
ulimit -Hn
echo 'es hard nproc 4096' >> /etc/security/limits.conf
echo 'es hard nproc 4096' >> /etc/security/limits.conf
echo 'config limints.conf success!'
# Config sysctl.conf
echo '====/etc/sysctl.conf===='
echo 'vm.max_map_count=262144' >> /etc/sysctl.conf
sysctl -p
echo 'config sysctl.conf success!'
# Install JDK
#echo 'install jdk start ......'
#rpm -i jdk-8u172-linux-x64.rpm
#shudoen firewall
systemctl stop firewalld.service
systemctl disable firewalld.service
#reboot to config vm.max_map_count
#echo 'will reboot to config vm.max_map_count'
#sleep 2
#reboot