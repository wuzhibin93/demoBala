package com.enlink.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:14 2019/1/17
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();
        System.out.println("PATH ==> " + servletPath);
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow_Origin","*");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
