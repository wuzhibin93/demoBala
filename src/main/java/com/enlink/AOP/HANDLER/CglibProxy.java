package com.enlink.AOP.HANDLER;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:44 2019/1/19
 */
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before==============切面加入逻辑");
        methodProxy.invokeSuper(o,objects);
        System.out.println("after===============切面加入逻辑");
        return null;
    }
}
