package com.enlink.de.src.main.java.com.enlink.controller;

import com.enlink.de.src.main.java.com.enlink.controller.common.AjaxResult;
import com.enlink.de.src.main.java.com.enlink.controller.common.HttpCode;
import com.enlink.de.src.main.java.com.enlink.controller.reqeust.LoginRequest;
import com.enlink.de.src.main.java.com.enlink.controller.response.UserDto;
import com.enlink.de.src.main.java.com.enlink.dao.UsersRepository;
import com.enlink.de.src.main.java.com.enlink.model.Users;
import com.enlink.de.src.main.java.com.enlink.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersController extends BaseController {
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/add")
    public AjaxResult add(@RequestBody Users model) {
        super.preSave(model);
        usersRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Users model) {
        usersRepository.saveAndFlush(model);
        return AjaxResult.ok(model);
    }

    @GetMapping("/delete")
    public AjaxResult delete(Integer id) {
        usersRepository.deleteById(id);
        return AjaxResult.ok(null);
    }

    @GetMapping("/find/{id}")
    public AjaxResult findById(@PathVariable Integer id) {
        Optional<Users> model = usersRepository.findById(id);
        return AjaxResult.ok(model.get());
    }

    @GetMapping("/find/all")
    public AjaxResult findAll() {
        List<Users> list = usersRepository.findAll();
        return AjaxResult.ok(list);
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String passwd = SecurityUtils.md5(loginRequest.getPassword());
        Users user = usersRepository.findByUsernameAndPassword(loginRequest.getUsername(), passwd);
        if (null != user) {
            UserDto ut = new UserDto();
            BeanUtils.copyProperties(user, ut);
            request.getSession().setAttribute("LOGIN_USER", ut);
            return AjaxResult.ok(ut);
        }
        return AjaxResult.errorOf(HttpCode.USERNAME_PASSWORD_ERROR, "用户名或密码错误");
    }
}
