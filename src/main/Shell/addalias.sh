#!/usr/bin/env bash
#ip地址 192.168.100.51:9200
ip_port=$1
#索引名称 res-filebeat  user-filebeat
index=$2

# 先添加别名
for i in `seq 1 12`
do
    i=`printf "%02d\n" ${i}`
    if [ ${i} -eq 02 ] ; then
        for e in `seq 30`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done
    elif [ ${i} -eq 04 ] ; then
        for e in `seq 30`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done
    elif [ ${i} -eq 06 ] ; then
        for e in `seq 30`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done
    elif [ ${i} -eq 09 ] ; then
        for e in `seq 30`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done
    elif [ ${i} -eq 11 ] ; then
        for e in `seq 30`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done
    else
        for e in `seq 31`
        do
            e=`printf "%02d\n" ${e}`
            curl -H "Content-Type:application/json" -XPUT ''${ip_port}'/'${2}'-2018.'${i}'.'${e}'/_alias/'${2}'-2018-'${i}'?pretty=true'
        done

    fi
done

