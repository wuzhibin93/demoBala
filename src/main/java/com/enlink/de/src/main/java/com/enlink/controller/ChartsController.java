/* 文件名: ChartsController.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件定义了Charts类型的控制器，实现了Charts对象的CURD方法。
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.de.src.main.java.com.enlink.controller;

import com.enlink.de.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.de.src.main.java.com.enlink.dao.ChartsRepository;
import com.enlink.de.src.main.java.com.enlink.model.Charts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ChartsController - Charts对象的控制器
 */
@RestController
@RequestMapping("/charts")
public class ChartsController extends BaseController {
    @Autowired
    private ChartsRepository chartsRepository;

    /**
     * add - 新增
     *
     * @param {Charts} model
     * @return {AjaxResult}
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Charts model) {
        super.preSave(model);
        chartsRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    /**
     * update - 更新
     *
     * @param {Charts} model
     * @return {AjaxResult}
     */
    @PostMapping("/update")
    public AjaxResult update(@RequestBody Charts model) {
        chartsRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    /**
     * delete - 删除
     *
     * @param {Integer} id
     * @return {AjaxResult}
     */
    @GetMapping("/delete")
    public AjaxResult delete(Integer id) {
        chartsRepository.deleteById(id);
        return AjaxResult.ok(null);
    }

    /**
     * findById - 根据Id查询
     *
     * @param {Integer} id
     * @return {AjaxResult}
     */
    @GetMapping("/find/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Optional<Charts> model = chartsRepository.findById(id);
        return AjaxResult.ok(model.get());
    }

    /**
     * findAll - 查询所有
     *
     * @return {AjaxResult}
     */
    @GetMapping("/find/all")
    public AjaxResult findAll() {
        List<Charts> list = chartsRepository.findAll();
        return AjaxResult.ok(list);
    }
}
