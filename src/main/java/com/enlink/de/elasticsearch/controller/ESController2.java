package com.enlink.de.elasticsearch.controller;

import com.enlink.de.elasticsearch.entity.ResourceLog;
import com.enlink.de.util.DateUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

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
