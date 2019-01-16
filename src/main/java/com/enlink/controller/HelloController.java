package com.enlink.controller;

import com.enlink.entity.Key;
import com.enlink.response.AjaxResult;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:24 2018/8/31
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("oo")
    private AjaxResults Hello(){
        System.out.println("ssss");
        return Results.resultOf(ResultCode.OK, "ok");
    }

    @RequestMapping("add")
    public AjaxResult add(Key key){
        return AjaxResult.ok(key);
    }
}
