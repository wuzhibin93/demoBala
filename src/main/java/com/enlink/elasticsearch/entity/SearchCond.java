package com.enlink.elasticsearch.entity;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(查询条件)
 * @Date : Created in 15:00 2018/9/11
 */
@Data
public class SearchCond {
    private int pageIndex;
    private int pageSize;
    private Map<String,String> terms;
    private Map<String,String> fuzziness;
    private Map<String,String> shoulds;
    private String keyword;
}
