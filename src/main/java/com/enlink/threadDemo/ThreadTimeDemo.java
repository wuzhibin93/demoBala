package com.enlink.threadDemo;

import java.text.SimpleDateFormat;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:33 2018/8/15
 */
public class ThreadTimeDemo {
    public static void main(String[] args) {
        System.out.println("线程1的操作");
        System.out.println("线程2的操作");
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        System.out.println(df.format(System.currentTimeMillis()));
        MyThread4 mt = new MyThread4();
        new Thread(mt).start();
        System.out.println("线程3的操作");
        System.out.println("线程4的操作");
    }
}

class MyThread4 implements Runnable{
    @Override
    public void run() {
        int temp = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            temp += i;
        }
    }
}
