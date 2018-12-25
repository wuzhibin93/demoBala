package com.enlink.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:09 2018/12/7
 */
@Data
public class Key {
    private String key_id;

    public Key(String key_id) {
        this.key_id = key_id;
    }
}
