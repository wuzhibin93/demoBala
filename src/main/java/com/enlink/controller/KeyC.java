package com.enlink.controller;

import com.enlink.entity.Key;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import com.enlink.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:43 2019/1/17
 */
@RestController
@RequestMapping("K")
public class KeyC {
    @Autowired
    private KeyService keyServiceImpl;

    @RequestMapping("te")
    public AjaxResults te(Key key){
        keyServiceImpl.testGlobal();
        return Results.resultOf(ResultCode.OK,key);
    }
}
