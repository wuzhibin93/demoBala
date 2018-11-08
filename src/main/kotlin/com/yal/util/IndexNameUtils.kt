package com.enlink.es.common

import java.util.*

/**
 * 获取metricbeat的索引名称
 */
fun metricbeatName(version: String): String {
    return "metricbeat-$version-${date2ym(Date(), ".")}*";
}

/**
 * 获取导出日志文件名
 */
fun getExportFileName(date: String, startTime: String, endTime: String): String {
    return "${date}-${startTime}~${endTime}.xlsx";
}


/**
 * 获取导出日志文件表头
 */
fun getHeaders(index: String): Map<String, String> {
    return when (index) {
        "user" -> mapOf(
                "log_time" to "日期",
                "search_user_info" to "用户信息",
                "auth_center" to "认证中心",
                "remote_ip" to "源ip",
                "search_operation_method" to "操作类型",
                "search_messages" to "信息"
        )
        "res" -> mapOf(
                "log_time" to "日期",
                "search_user_info" to "用户信息",
                "app_type" to "通道方式",
                "remote_ip" to "源ip",
                "upward_flow" to "上行流量",
                "downward_flow" to "下行流量",
                "search_messages" to "信息"
        )
        "system" -> mapOf(
                "log_time" to "日期",
                "log_level" to "级别",
                "log_info" to "信息"
        )
        "admin" -> mapOf(
                "log_time" to "日期",
                "search_user_info" to "用户信息",
                "right_name" to "管理员权限",
                "remote_ip" to "源ip",
                "search_operation_method" to "操作",
                "search_messages" to "信息"
        )
        else -> emptyMap()
    }
}