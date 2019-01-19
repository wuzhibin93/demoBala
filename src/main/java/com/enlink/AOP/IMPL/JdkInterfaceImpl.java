package com.enlink.AOP.IMPL;

import com.enlink.AOP.INTERFACE.JdkInterface;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:32 2019/1/19
 */
public class JdkInterfaceImpl implements JdkInterface {
    @Override
    public void add() {
        System.out.println("目标类的add方法");
    }
}
