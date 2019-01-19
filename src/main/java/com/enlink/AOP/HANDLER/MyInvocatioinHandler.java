package com.enlink.AOP.HANDLER;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:34 2019/1/19
 */
public class MyInvocatioinHandler implements InvocationHandler {
    private Object target;

    public MyInvocatioinHandler(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before---------切面加入逻辑");
        Object invoke = method.invoke(target, args);
        System.out.println("after---------切面加入逻辑");
        return invoke;

    }
}
