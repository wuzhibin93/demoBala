#!/usr/bin/env bash
curl -H "Content-Type:application/json" -XPUT 'localhost:9200/_template/enlink?pretty=true' -d'
{
    "template" : "*-filebeat-*",
    "order" : "1",
    "aliases" : {
        "alias_1" : {}
    },
    "settings" : {
        "index": {
        "mapping": {
            "total_fields": {
                "limit": "10000"
            }
        },
        "refresh_interval": "5s",
        "number_of_routing_shards": "30",
        "number_of_shards": "5"
        }
    },
    "mappings" : {
		"doc": {
            "dynamic_templates": [
                {
                    "ip":{
                        "match":"*_ip",
                        "mapping":{
                            "type":"ip"
                        }
                    }
                },
                {
                    "longitude":{
                        "match":"longitude",
                        "mapping":{
                            "type":"float"
                        }
                    }
                },
                {
                    "latitude":{
                        "match":"latitude",
                        "mapping":{
                            "type":"float"
                        }
                    }
                },
                {
                    "time":{
                        "match":"*_timestamp",
                        "mapping":{
                            "type":"date",
							"format" : "strict_date_optional_time||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS||dd/MMM/yyyy:HH:mm:ss Z||epoch_millis"
                        }
                    }
                },
				{
                    "*": {
                        "match_mapping_type": "string",
                        "mapping": {
                            "type": "keyword"
                        }
                    }
                }
            ],
		    "_source" : {"enabled":true},
            "properties": {
			"@timestamp": {
                "type" : "date",
				"format" : "strict_date_optional_time||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS||dd/MMM/yyyy:HH:mm:ss Z||epoch_millis"
			}
		}
	}
}
}
'
