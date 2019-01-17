package com.enlink.controller;

import com.enlink.entity.GlobalParameterT;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:58 2019/1/17
 */
@Slf4j
@RestController
@RequestMapping("/T")
public class GlobalParameterC {

    @Autowired
    private GlobalParameterT globalParameterT;


    @RequestMapping("S")
    public AjaxResults T(){
        System.out.println(globalParameterT.getHost());
        LOGGER.error("test global and @Slf4j");
        return Results.resultOf(ResultCode.OK, globalParameterT.getHost());
    }
}
