#!/usr/bin/env bash
# 传入的时间格式 20180205
#index="twitter"
#time_style="@timestamp"
#start_time="20181227"
#end_time="20181229"
# 使用方式 ./index_newIndexWithDate.sh index startTime endTime
# eg: ./index_newIndexWithDate.sh twitter 20180202 20181231
# 循环遍历时间
index=$1
time_style=$2
start_time=$3
end_time=$4
witch_time=""
ip_port="192.168.100.51:9200"
# 先做备份
curl -H "Content-Type:application/json" -XPOST ''${ip_port}'/_reindex?pretty=true' -d'
    {
        "source": {
            "index": "'${1}'"
            },
        "dest":{
            "index": "'${1}'-backup"
      }
    }
    '
while [ "${start_time}" -le "${end_time}" ]
do
    # reindex用的时间格式
	stat_date=`date -d "${start_time}" +%Y-%m-%d`
	# 命名索引用的时间格式
	index_time=`date -d "${start_time}" +%Y.%m.%d`
	stat_date_num=`date -d "${start_time}" +%Y%m%d`
	echo "stat_date---------"${stat_date}
	start_time=$(date -d "${start_time}+1days" +%Y%m%d)
    if [ "${index}" == "${index%-*}-${index_time}" ]; then
        witch_time=${index_time}
        curl -H "Content-Type:application/json" -XPOST ''${ip_port}'/_reindex?pretty=true' -d'
    {
        "source": {
        "index": "'${1}'",
        "query": {
          "range": {
            "'${time_style}'": {
              "gte": "'${stat_date}'",
              "lte": "'${stat_date}'"
            }
          }
        }
      },
      "dest":{
        "index": "'${index%-*}'"
      }
    }
    '
    else
    echo 2
	curl -H "Content-Type:application/json" -XPOST ''${ip_port}'/_reindex?pretty=true' -d'
    {
        "source": {
        "index": "'${1}'",
        "query": {
          "range": {
            "'${time_style}'": {
              "gte": "'${stat_date}'",
              "lte": "'${stat_date}'"
            }
          }
        }
      },
      "dest":{
        "index": "'${index%-*}'-'${index_time}'"
      }
    }
    '
    fi
done
curl -H "Content-Type:application/json" -XDELETE ''${ip_port}'/'${1}'?pretty=true'
curl -H "Content-Type:application/json" -XPOST ''${ip_port}'/_reindex?pretty=true' -d'
    {
        "source": {
            "index": "'${index%-*}'"
    },
        "dest":{
            "index": "'${1}'"
        }
    }
    '
curl -H "Content-Type:application/json" -XDELETE ''${ip_port}'/'${index%-*}'?pretty=true'

