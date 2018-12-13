package com.enlink.src.main.java.com.enlink.controller;

import com.enlink.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.src.main.java.com.enlink.dao.PanelChartMappingRepository;
import com.enlink.src.main.java.com.enlink.model.PanelChartMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/panelChartMapping")
public class PanelChartMappingController extends BaseController {
    @Autowired
    private PanelChartMappingRepository panelChartMappingRepository;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody PanelChartMapping model) {
        super.preSave(model);
        panelChartMappingRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody PanelChartMapping model) {
        panelChartMappingRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @GetMapping("/delete")
    public AjaxResult delete(Integer id) {
        panelChartMappingRepository.deleteById(id);
        return AjaxResult.ok(null);
    }

    @GetMapping("/find/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Optional<PanelChartMapping> model = panelChartMappingRepository.findById(id);
        return AjaxResult.ok(model.get());
    }

    @GetMapping("/find/all")
    public AjaxResult findAll() {
        List<PanelChartMapping> list = panelChartMappingRepository.findAll();
        return AjaxResult.ok(list);
    }
}
