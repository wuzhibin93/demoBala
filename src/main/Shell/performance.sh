#!/usr/bin/env bash
KEYWORDS_TXT="./keywords.txt"
cat /dev/null > ./rst.txt

echo "beginTime=`date`"

cat $KEYWORDS_TXT | while read line
do
echo "line=$line"
echo "curl -XGET http://100.10.11.130:9200/shx_info_index/shx_info_type/_search -d'
{
  \"query\" : {
  \"query_string\" : {
  \"default_field\" : \"company_name\",
  \"query\" : \"$line\"
  }
  }
}' >> ./rst.txt 2>&1 & " >> ./sql.txt

icnt=$((icnt+1));
echo "icnt ="${icnt};
done;
