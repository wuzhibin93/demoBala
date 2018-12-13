package com.enlink.elasticsearch.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:47 2018/8/31
 */
@Data
public class User extends GeneralModel{
    private String username;
    private String password;
    private String work;
    private String brith;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getBrith() {
        return brith;
    }

    public void setBrith(String brith) {
        this.brith = brith;
    }

    public User() {
    }

    public User(String username, String password, String work, String brith) {
        this.username = username;
        this.password = password;
        this.work = work;
        this.brith = brith;
    }
}
