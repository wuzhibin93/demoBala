package com.enlink.controller;

import com.enlink.Exception.CustomException;
import com.enlink.dao.MyUserDao;
import com.enlink.entity.Key;
import com.enlink.entity.MyUser;
import com.enlink.entity.User;
import com.enlink.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    @PostMapping("login")
    private AjaxResult login(MyUser myUser){
        List<MyUser> myUsers = myUserDao.listByUsername(myUser.getUsername(), myUser.getPassword());
        System.out.println(myUser+"===========");
        if (null != myUser){
            //httpRequest.getSession().setAttribute("LOGIN_USER",myUser.getId());
            return AjaxResult.ok("登录成功");
        }
        return AjaxResult.errorOf(HttpCode.USERNAME_PASSWORD_ERROR,"登录失败");
    }

    @RequestMapping("add")
    public AjaxResult add(String  key){
        if (key.isEmpty()){
            throw new CustomException(400,"num不能为空");
        }
        return AjaxResult.ok(key);
    }

    @RequestMapping("adduser")
    public AjaxResult addUser(User user){
        System.out.println(user);
        myUserDao.insertUser(user);
        return AjaxResult.ok(user);
    }
}
