package com.enlink.de.elasticsearch.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 管理员日志
 *
 * @author changgq
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AdminLog extends GeneralModel {
    // 日志格式：INFO|2018-07-03 08:57:10|登录管理端|10|zhoujing||普通管理员|192.168.5.182|43027782-a8d0-4556-b4d0-1caeef9b3173|成功|
    // 日志格式：INFO|2018-08-24 18:22:15|Windows|-|修改|更新用户信息|1|admin|-|超级管理员|192.168.5.125|3ddb6168-ab70-41f6-9433-5c02a2280012|成功|
    // 日志格式：log_level|log_time|os|right_name|operation_method|log_info|user_id|user_name|full_name|role_name|remote_ip|session_id|keyword_status|

    // 日志级别
    private String log_level;
    // 日志时间
    private String log_timestamp;
    // 操作系统
    private String os;
    // 权限名称：由原来角色字段替换
    private String right_name;
    // 操作方式
    private String operation_method;
    // 日志详细信息
    private String log_info;
    // 用户id
    private String user_id;
    // 用户名
    private String user_name;
    // 全名
    private String full_name;
    // 角色：由用户组字段替
    private String role_name;
    // 源ip
    private String ip_address;
    // sessionId
    private String session_id;
    // 操作状态：成功、失败、阅读、接收、收藏、取消收藏
    // SUCCESS、FAIL、READ、RECEIVE、
    private String keyword_status;

}
