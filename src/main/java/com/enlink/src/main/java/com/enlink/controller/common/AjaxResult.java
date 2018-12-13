/* 文件名: AjaxResult.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件定义了Ajax请求的响应格式，及成功和失败时的默认方法。
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.src.main.java.com.enlink.controller.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;

/**
 * AjaxResult - Ajax请求响应结果的格式
 */
@Data
@AllArgsConstructor
public class AjaxResult implements Serializable {
    private String code;
    private String messages;
    private Object data;

    /**
     * ok - Http请求正常执行时，默认的返回结果
     *
     * @param {Object} data
     * @return {AjaxResult} 返回对象本身
     */
    public static AjaxResult ok(Object data) {
        return new AjaxResult(HttpCode.OK.getCode(), HttpCode.OK.getDesc(), data);
    }

    /**
     * errorOf - Http请求异常时，默认的返回结果
     *
     * @param {HttpCode} httpCode
     * @param {String} messages
     * @return {AjaxResult} 返回对象本身
     */
    public static AjaxResult errorOf(HttpCode httpCode, String messages) {
        return new AjaxResult(httpCode.getCode(), Strings.isNotBlank(messages) ? messages : httpCode.getDesc(), null);
    }
}


