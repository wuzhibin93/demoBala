package com.enlink.elasticsearch.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:42 2018/9/13
 */
@Data
public class InsertTime {
    private String id;
    private String times;
    private String user;
    private String method;
    private String totaltime;
    private String totalsize;
    private String logtime;
    private String thatIndexTotalSize;//当前索引下的数据总数

    public InsertTime(String id, String times, String user, String method, String totaltime, String totalsize, String logtime) {
        this.id = id;
        this.times = times;
        this.user = user;
        this.method = method;
        this.totaltime = totaltime;
        this.totalsize = totalsize;
        this.logtime = logtime;
    }

    public InsertTime(String id, String times, String user, String method, String totaltime, String totalsize, String logtime, String thatIndexTotalSize) {
        this.id = id;
        this.times = times;
        this.user = user;
        this.method = method;
        this.totaltime = totaltime;
        this.totalsize = totalsize;
        this.logtime = logtime;
        this.thatIndexTotalSize = thatIndexTotalSize;
    }
}
