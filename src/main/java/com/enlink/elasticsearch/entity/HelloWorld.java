package com.enlink.elasticsearch.entity;

import org.springframework.stereotype.Component;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:26 2018/8/31
 */
@Component
public class HelloWorld {
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
