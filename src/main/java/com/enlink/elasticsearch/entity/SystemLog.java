package com.enlink.elasticsearch.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 系统日志
 *
 * @author changgq
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SystemLog extends GeneralModel {
    // 日志格式：WARNING|2018-08-20 14:50:47|CPU|CPU到达70%

    // 日志级别
    private String log_level;
    // 日志时间
    private String  log_timestamp;
    // 操作方式：CPU、HDD、RAM、网卡流量、服务名称
    private String operate_type;
    // 日志详细信息
    private String log_info;
}
