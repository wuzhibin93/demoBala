package com.enlink.de.controller;

import com.enlink.de.elasticsearch.entity.HelloWorld;
import com.enlink.de.response.AjaxResults;
import com.enlink.de.response.ResultCode;
import com.enlink.de.response.Results;
import org.springframework.beans.factory.annotation.Autowired;
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
}
