#!/usr/bin/env bash
#ip地址 192.168.100.51:9200
ip_port=$1
#索引名称 res-filebeat  user-filebeat
index=$2
for i in `seq 1 12`
do
i=`printf "%02d\n" ${i}`
# 聚合查询-查询出当前月用户登录数
# 需要用户索引
echo "每个月用户登录数" >> a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
                "size": 0,
                 "query": {
                    "match_all": {}
                 }
            }
            ' >> a.txt
# 需要用户索引
echo "用户登录榜" >> a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
              "size": 0,
              "aggs": {
                "max_login": {
                  "terms": {
                    "field": "search_user_info.keyword",
                    "size": 10
                  }
                }
              }
            }
            ' >> a.txt


# 需要资源索引
# 用户访问数-这个月用户总数-资源被访问次数
echo "用户访问数" >> a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
                "size": 0,
                 "query": {
                    "match_all": {}
                 }
            }
            ' >> a.txt


# 用户访问榜-哪个资源被访问的最多
echo "用户访问榜" >> /a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
              "size": 0,
              "aggs": {
                "max_make": {
                  "terms": {
                    "field": "session.keyword",
                    "size": 10
                  }
                }
              }
            }
            ' >> a.txt
# 应用访问数-应用被访问次数
echo "应用访问数" >> a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
              "size": 0,
              "aggs": {
                "max_make": {
                  "terms": {
                    "field": "url.keyword"
                  }
                }
              }
            }
            ' >> a.txt

echo "应用访问榜" >> a.txt
curl -H "Content-Type:application/json" -XGET ''${ip_port}'/'${2}'-2018-'${i}'/_search?pretty=true' -d'
            {
              "size": 0,
              "aggs": {
                "max_view": {
                  "terms": {
                    "field": "url.keyword",
                    "size": 10
                  }
                }
              }
            }' >> a.txt
done
read -p "Press any key to continue." var


