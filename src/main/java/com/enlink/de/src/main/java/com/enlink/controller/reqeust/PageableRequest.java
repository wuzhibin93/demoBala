package com.enlink.de.src.main.java.com.enlink.controller.reqeust;

import lombok.Data;

import java.util.Map;

@Data
public class PageableRequest {
    private Integer pageIndex;
    private Integer pageSize;
    private Map<String, String> sorts;
    private Map<String, Object> conditions;
}
