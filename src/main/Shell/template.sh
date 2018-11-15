#!/usr/bin/env bash
curl -H "Content-Type:application/json" -XPUT 'localhost:9200/_template/enlink?pretty=true' -d'
{
    "template" : "*",
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
        "number_of_shards": "1"
        }
    },
    "mappings" : {
		"doc": {
            "dynamic_templates": [
                {
                    "traffic":{
                        "match":"*_traffic",
                        "mapping":{
                            "type":"long"
                        }
                    }
                },
                {
				    "to_long":{
				        "match":"*_long",
				        "mapping":{
				            "type":"long"
				        }
				    }
				},
                {
                    "ip":{
                        "match":"*_ip",
                        "mapping":{
                            "type":"ip"
                        }
                    }
                },
                {
					"geo":{
						"path_match":"geoip.location.*",
						"mapping":{
							"type": "float"
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
