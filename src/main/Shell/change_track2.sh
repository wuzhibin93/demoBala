#!/usr/bin/env bash

#Each time the data source changes, the corresponding track.json also changes
echo "Add data with /usr/local/foresrally.py"
python /usr/local/foresrally.py

#tar document.json
tar cvjPf /usr/local/esrally/benchmarks/data/enadmin/documents.json.bz2 /usr/local/esrally/benchmarks/data/enadmin/documents.json

#Change the track.json
echo "Change the track.json"
document_count=`wc -l < /usr/local/esrally/benchmarks/data/enadmin/documents.json`
compressed_bytes=`wc -c < /usr/local/esrally/benchmarks/data/enadmin/documents.json.bz2`
uncompressed_bytes=`wc -c < /usr/local/esrally/benchmarks/data/enadmin/documents.json`
echo document_count:${document_count}
echo compressed:${compressed_bytes}
echo uncpmpressed:${uncompressed_bytes}
sed -i "19c \"document-count\": ${document_count}," /usr/local/esrally/benchmarks/tracks/enadmin/track.json
sed -i "20c \"compressed-bytes\": ${compressed_bytes}," /usr/local/esrally/benchmarks/tracks/enadmin/track.json
sed -i "21c \"uncompressed-bytes\": ${uncompressed_bytes}" /usr/local/esrally/benchmarks/tracks/enadmin/track.json

#Whene change the track.json,will run esrally
echo "Run esrally with the track.json has changed"
#esrally race --pipeline=benchmark-only --target-hosts=localhost:9200 --track-path=/usr/local/esrally/benchmarks/tracks/enadmin/ --car="2gheap"  --user-tag="data:200000,car:2gheap"
for i in 1 2 4 8 16 32; do
	echo ${i}
        for l in 0 1 2 3 4 5 6 7 8 9; do
        echo "Start ${document_count}数据量,${i}gheap的第${l} 次esrally at :"`date +"%Y-%m-%d %H:%M:%S"` >> /usr/local/esrally.log
		#esrally race --pipeline=benchmark-only --target-hosts=localhost:9200 --track-path=/usr/local/esrally/benchmarks/tracks/enadmin/ --car="${i}gheap"  --user-tag="data:${document_count},car:${i}gheap"
		echo "End  ${document_count}数据量,${i}gheap的第${l} 次esrally at:"`date +"%Y-%m-%d %H:%M:%S"` >> /usr/local/esrally.log
	done
done


