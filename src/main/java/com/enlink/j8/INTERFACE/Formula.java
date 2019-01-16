package com.enlink.j8.INTERFACE;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:22 2019/1/16
 */
public interface Formula {
    //计算
    double calculate(int a);
    //求平方根
    default double sqrt(int a ){
        return Math.sqrt(a);
    }
}
