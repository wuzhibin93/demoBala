#!/usr/bin/env bash

# 用户登录数
POST /user-filebeat-2018.*/_search
{
    "size": 0,
    "aggs" : {
        "login_per_month" : {
            "date_histogram" : {
                "field" : "@timestamp",
                "interval" : "month"
            },
            "aggs": {
                "name": {
                    "value_count": {
                        "field": "user_name"
                    }
                }
            }
        }
    }
}

# 用户登录榜-按月份分
POST /user-filebeat-2018.*/_search?size=0
{
  "aggs": {
    "NAME": {
      "terms": {
        "field": "user_name",
        "size": 10
      }
    }
  }
}
# 用户访问数
POST res-filebeat-2018.*/_search?size=0
{
  "aggs": {
    "view_per_month": {
      "date_histogram": {
        "field": "@timestamp",
        "interval": "month"
      },
      "aggs": {
        "type_count": {
          "cardinality": {
            "field": "account"
          }
        }
      }
    }
  }
}
# 用户访问榜
POST /res-filebeat-2018.*/_search?size=0
{
  "aggs": {
    "NAME": {
      "terms": {
        "field": "account",
        "size": 10
      }
    }
  }
}
# 应用访问数
POST res-filebeat-2018.*/_search?size=0
{
  "aggs": {
    "view_per_month": {
      "date_histogram": {
        "field": "@timestamp",
        "interval": "month"
      },
      "aggs": {
        "type_count": {
          "cardinality": {
            "field": "url"
          }
        }
      }
    }
  }
}

# 应用访问榜
POST /res-filebeat-2018.*/_search?size=0
{
  "aggs": {
    "NAME": {
      "terms": {
        "field": "url",
        "size": 10
      }
    }
  }
}