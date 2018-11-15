package com.enlink.de.src.main.java.com.enlink.controller;

import com.enlink.de.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.de.src.main.java.com.enlink.controller.reqeust.ModulesTreeRequest;
import com.enlink.de.src.main.java.com.enlink.controller.reqeust.PageableRequest;
import com.enlink.de.src.main.java.com.enlink.controller.response.ModuleTreeDto;
import com.enlink.de.src.main.java.com.enlink.dao.ModulesRepository;
import com.enlink.de.src.main.java.com.enlink.model.Modules;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/modules")
public class ModulesController extends BaseController {
    @Autowired
    private ModulesRepository modulesRepository;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Modules model) {
        super.preSave(model);
        if (null == model.getParentId()) {
            model.setParentId(-1);
        }
        modulesRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Modules model) {
        modulesRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @GetMapping("/delete")
    public AjaxResult delete(Integer id) {
        modulesRepository.deleteById(id);
        return AjaxResult.ok(null);
    }

    @GetMapping("/find/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Optional<Modules> model = modulesRepository.findById(id);
        return AjaxResult.ok(model.get());
    }

    @GetMapping("/find/all")
    public AjaxResult findAll() {
        List<Modules> list = modulesRepository.findAll();
        return AjaxResult.ok(list);
    }

    @PostMapping("/find/paging")
    public AjaxResult findByPaging(@RequestBody PageableRequest pageableRequest) {
        Page<Modules> pagings = modulesRepository.findAll(super.genPageable(pageableRequest));
        return AjaxResult.ok(super.genPagingResult(pagings));
    }

    @PostMapping("/modulesTree")
    public AjaxResult modulesTree(@RequestBody ModulesTreeRequest modulesTreeRequest) {
        return AjaxResult.ok(genModulestree(modulesTreeRequest));
    }

    /**
     * genModulestree - 生成模块树形结构
     *
     * @param modulesTreeRequest
     * @return
     */
    private List<ModuleTreeDto> genModulestree(ModulesTreeRequest modulesTreeRequest) {
        List<ModuleTreeDto> tree = new ArrayList<>();
        List<Modules> modulesList = modulesRepository.findByParentId(modulesTreeRequest.getId());
        for (Modules m : modulesList) {
            ModuleTreeDto mt = new ModuleTreeDto();
            BeanUtils.copyProperties(m, mt);
            if (modulesTreeRequest.isAllChildren()) {
                modulesTreeRequest.setId(m.getId());
                mt.setChildren(genModulestree(modulesTreeRequest));
            }
            tree.add(mt);
        }
        return tree;
    }
}
