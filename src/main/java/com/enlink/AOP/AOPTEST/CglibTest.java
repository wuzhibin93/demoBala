package com.enlink.AOP.AOPTEST;

import com.enlink.AOP.HANDLER.CglibProxy;
import com.enlink.AOP.IMPL.Base;
import com.enlink.util.FanSheUtils;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:47 2019/1/19
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Base.class);
        //回调方法的参数为代理类对象CglibProxy,最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
        enhancer.setCallback(cglibProxy);
        Base base = (Base)enhancer.create();
        base.add();

        Class<? extends Base> aClass = base.getClass();
        //查看增强过的类的父类是不是未增强的Base类
        System.out.println("增强过的类的父类："+aClass.getSuperclass().getName());
        System.out.println("============打印增强过的类的所有方法==============");
        FanSheUtils.printMethods(aClass);

        //没有被增强过的base类
        Base base1 = new Base();
        System.out.println("未被增强过的类的父类： "+base1.getClass().getSuperclass().getName());
        System.out.println("============打印未增强过的类的所有方法==============");
        FanSheUtils.printMethods(base1.getClass());

    }
}
