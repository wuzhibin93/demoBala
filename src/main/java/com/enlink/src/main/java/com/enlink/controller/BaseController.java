/* 文件名: BaseController.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件定义了基础Controller，包含一些通用的方法。其他控制器类，通过继承该类使用公用方法。
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.src.main.java.com.enlink.controller;


import com.enlink.src.main.java.com.enlink.controller.reqeust.PageableRequest;
import com.enlink.src.main.java.com.enlink.model.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * BaseController - 基础控制器
 */
public class BaseController {

    /**
     * preSave - 保存方法前执行的操作
     *
     * @param {T extends BaseModel} model
     */
    public void preSave(BaseModel model) {
        Date timestamp = new Date();
        if (model.getId() == null) {
            model.setCreate_at(timestamp);
        }
        model.setUpdate_at(timestamp);
    }

    public Pageable genPageable(PageableRequest pageableRequest) {
        List<Sort.Order> orders = new ArrayList<>();
        if (null != pageableRequest.getSorts() && pageableRequest.getSorts().values().size() > 0) {
            for (String sortKey : pageableRequest.getSorts().keySet()) {
                orders.add(Sort.Order.by(sortKey));
            }
        } else {
            orders.add(Sort.Order.desc("id"));
        }
        return PageRequest.of(pageableRequest.getPageIndex() - 1, pageableRequest.getPageSize(), Sort.by(orders));
    }

    public Map<String, Object> genPagingResult(Page page) {
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotalElements());
        data.put("data", page.getContent());
        return data;
    }
}
