#!/usr/bin/env bash
# 传入的时间格式 20180205
#index="twitter"
#start_time="20181227"
#end_time="20181229"

# 循环遍历时间
index=$1
start_time=$2
end_time=$3
while [ "${start_time}" -le "${end_time}" ]
do
	stat_date=`date -d "${start_time}" +%Y-%m-%d`
	stat_date_num=`date -d "${start_time}" +%Y%m%d`
	echo "stat_date---------"${stat_date}
	start_time=$(date -d "${start_time}+1days" +%Y%m%d)
	curl -H "Content-Type:application/json" -XPOST '192.168.100.51:9200/_reindex?pretty=true' -d'
    {
        "source": {
        "index": "'${1}'",
        "query": {
          "range": {
            "@timestamp": {
              "gte": "'${stat_date}'",
              "lte": "'${stat_date}'"
            }
          }
        }
      },
      "dest": {
        "index": "twitter-'${stat_date}'"
      }
    }
    '
done

