package com.enlink.controller;

import com.enlink.dao.MyUserDao;
import com.enlink.entity.Key;
import com.enlink.entity.User;
import com.enlink.response.AjaxResult;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:24 2018/8/31
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private MyUserDao myUserDao;


    @RequestMapping("oo")
    private AjaxResults Hello(){
        System.out.println("ssss");
        return Results.resultOf(ResultCode.OK, "ok");
    }

    @RequestMapping("add")
    public AjaxResult add(Key key){
        return AjaxResult.ok(key);
    }

    @RequestMapping("adduser")
    public AjaxResult addUser(User user){
        System.out.println(user);
        myUserDao.insertUser(user);
        return AjaxResult.ok(user);
    }
}
