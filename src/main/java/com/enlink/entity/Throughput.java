package com.enlink.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:44 2018/12/7
 */
@Data
public class Throughput {
    private String id;
    private String unit;
    private String min;
    private String median;
    private String max;
}
