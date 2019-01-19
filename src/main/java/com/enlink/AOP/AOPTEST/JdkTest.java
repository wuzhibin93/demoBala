package com.enlink.AOP.AOPTEST;

import com.enlink.AOP.HANDLER.MyInvocatioinHandler;
import com.enlink.AOP.IMPL.JdkInterfaceImpl;
import com.enlink.AOP.INTERFACE.JdkInterface;

import java.lang.reflect.Proxy;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:37 2019/1/19
 */
public class JdkTest {
    public static void main(String[] args) {
        JdkInterfaceImpl jdkInterfaceImpl = new JdkInterfaceImpl();
        MyInvocatioinHandler myInvocatioinHandler = new MyInvocatioinHandler(jdkInterfaceImpl);
        //Proxy为InvocationHandler实现类动态创建一个符合某一接口的代理实例
        //这里的proxyInstance就是我们目标类的增强代理类
        JdkInterface jdkInterface = (JdkInterface)Proxy.newProxyInstance(jdkInterfaceImpl.getClass().getClassLoader(), jdkInterfaceImpl.getClass().getInterfaces(), myInvocatioinHandler);
        jdkInterface.add();
        //打印增强过的类类型
        System.out.println("==============="+jdkInterface.getClass());
    }
}
