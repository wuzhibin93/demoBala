package com.enlink.de.elasticsearch.service.serviceImpl;

import com.enlink.de.config.ElasticsearchConfig;
import com.enlink.de.elasticsearch.entity.User;
import com.enlink.de.elasticsearch.service.UserService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:38 2018/8/31
 */
public class UserServiceImpl implements UserService{
    @Autowired
    private ElasticsearchConfig esConfig;

    @Autowired
    private RestHighLevelClient esClient;

    @Autowired
    //private JestClient jsC;

    @Override
    public void saveUser(User user) {


    }
}
