package com.enlink.service.impl;

import com.enlink.entity.Key;
import com.enlink.service.KeyService;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:21 2018/12/25
 */
public class KeyServiceImpl implements KeyService {

    @Override
    public void insertKey(Key key) {

    }

    public RestHighLevelClient client(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.100.51", 9200, "http")
                )
        );
        return client;
    }
}
