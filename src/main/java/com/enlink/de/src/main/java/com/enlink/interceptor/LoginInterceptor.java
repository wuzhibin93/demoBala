/* 文件名: LoginInterceptor.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件定义了登录Interceptor，实现用户登录认证的校验。
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.de.src.main.java.com.enlink.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        HttpSession httpSession = request.getSession();
        if (null != httpSession.getAttribute("LOGIN_USER")) {
            return true;
        }
        String path = request.getServletPath();
        if (path.indexOf("/static") >= 0
                || path.indexOf("/users/login") >= 0) {
            return true;
        } else {
            response.sendRedirect("/auth/cas");
        }
        return false;
    }
}
