/* 文件名: HttpCode.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件定义了Ajax请求的响应结果代码及说明
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.response;

/**
 * HttpCode - Ajax请求的响应结果代码及说明
 */
public enum HttpCode {
    OK("200", "OK"),
    CREATED("201", "Created"),
    ACCEPTED("202", "Accepted"),
    NON_AUTHORITATIVE_INFORMATION("203", "Non-Authoritative Information"),
    NO_CONTENT("204", "No Content"),
    RESET_CONTENT("205", "Reset Content"),
    PARTIAL_CONTENT("206", "Partial Content"),
    MULTI_STATUS("207", "Multi Status"),
    IM_USED("226", "Im Used"),

    // redirection
    MULTIPLE_CHOICES("300", "Multiple Choices"),
    MOVED_PERMANENTLY("301", "Moved Permanently"),
    FOUND("302", "Found"),
    SEE_OTHER("303", "See Other"),
    NOT_MODIFIED("304", "Not Modified"),
    USE_PROXY("305", "Use Proxy"),
    TEMPORARY_REDIRECT("307", "Temporary Redirect"),

    // client error
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    PAYMENT_REQUIRED("402", "Payment Required"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    NOT_ACCEPTABLE("406", "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED("407", "Proxy Authentication Required"),
    REQUEST_TIMEOUT("408", "Request Timeout"),
    CONFLICT("409", "Conflict"),
    GONE("410", "Gone"),
    LENGTH_REQUIRED("411", "Length Required"),
    PRECONDITION_FAILED("412", "Precondition Failed"),
    REQUEST_ENTITY_TOO_LARGE("413", "Request Entity Too Large"),
    REQUEST_URI_TOO_LONG("414", "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE("415", "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE("416", "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED("417", "Expectation Failed"),
    UNPROCESSABLE_ENTITY("422", "Unprocessable Entity"),
    LOCKED("423", "Locked"),
    FAILED_DEPENDENCY("424", "Failed Dependency"),
    UPGRADE_REQUIRED("426", "Upgrade Required"),
    PRECONDITION_REQUIRED("428", "Precondition Required"),
    TOO_MANY_REQUESTS("429", "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE("431", "Request Header Fields Too Large"),

    // server error
    INTERNAL_SERVER_ERROR("500", "内部服务错误"),
    NOT_IMPLEMENTED("501", "Not Implemented"),
    BAD_GATEWAY("502", "Bad Gateway"),
    SERVICE_UNAVAILABLE("503", "Service Unavailable"),
    GATEWAY_TIMEOUT("504", "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED("505", "HTTP Version Not Supported"),
    INSUFFICIENT_STORAGE("507", "Insufficient Storage"),
    NOT_EXTENDED("510", "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED("511", "Network Authentication Required"),

    // system error
    SYSTEM_ERROR("1000", "系统异常"),
    REQUEST_PARAMES_ERROR("1001", "请求参数错误"),
    DATA_SEARCH_TIMEOUT("1002", "数据查询超时"),
    REQUEST_TERMINATED("1003", "请求被终止"),
    USERNAME_PASSWORD_ERROR("1004", "用户或密码错误"),
    ID_IS_NULL("1005", "ID不能为空。"),
    NAME_IS_EXISTS("1006", "名称已存在。"),
    UPDATE_DATA_IS_NOT_EXISTS("1007", "更新的数据不存在！"),
    CREATE_ID_IS_NO_NEED("1008", "新增功能，请不要添加ID字段。"),
    DELETE_DATA_IS_NOT_EXISTS("1009", "删除的数据不存在");


    private String code;
    private String desc;

    /**
     * HttpCode - 私有构造方法
     *
     * @param {String} code
     * @param {String} message
     * @desc 私有构造方法定义本Enum的内容，不允许new对象。
     */
    private HttpCode(String code, String message) {
        this.code = code;
        this.desc = message;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
