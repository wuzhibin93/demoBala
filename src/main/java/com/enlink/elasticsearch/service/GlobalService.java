package com.enlink.elasticsearch.service;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:12 2018/8/31
 */

public interface GlobalService {
    @Bean
    RestHighLevelClient getClient();
}
