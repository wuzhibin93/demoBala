package com.enlink.de.src.main.java.com.enlink.controller;

import com.enlink.de.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.de.src.main.java.com.enlink.dao.PanelsRepository;
import com.enlink.de.src.main.java.com.enlink.model.Panels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/panels")
public class PanelsController extends BaseController {
    @Autowired
    private PanelsRepository panelsRepository;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Panels model) {
        super.preSave(model);
        panelsRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Panels model) {
        panelsRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @GetMapping("/delete")
    public AjaxResult delete(Integer id) {
        panelsRepository.deleteById(id);
        return AjaxResult.ok(null);
    }

    @GetMapping("/find/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Optional<Panels> model = panelsRepository.findById(id);
        return AjaxResult.ok(model.get());
    }

    @GetMapping("/find/all")
    public AjaxResult findAll() {
        List<Panels> list = panelsRepository.findAll();
        return AjaxResult.ok(list);
    }
}
