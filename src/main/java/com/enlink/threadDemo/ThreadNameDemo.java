package com.enlink.threadDemo;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:19 2018/8/15
 */
public class ThreadNameDemo {
    public static void main(String[] args) {
        MyThread3 mt =new MyThread3();
        new Thread(mt,"线程A").start();
        new Thread(mt,"线程B").start();
        new Thread(mt).start();
        new Thread(mt).start();
    }
}

class MyThread3 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}