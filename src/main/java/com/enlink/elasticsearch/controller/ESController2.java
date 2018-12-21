package com.enlink.elasticsearch.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 10:15 2018/9/13
 */
@RestController
@RequestMapping("ES")

public class ESController2 {

    RestHighLevelClient client = getElasticSearchClient();

    //获取客户端
    public RestHighLevelClient getElasticSearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.63.179", 9200, "http"),
                        new HttpHost("192.168.63.181", 9200, "http"),
                        new HttpHost("192.168.63.182", 9200, "http"),
                        new HttpHost("192.168.63.183", 9200, "http")
                )
        );
    }
}
