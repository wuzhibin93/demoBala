package com.enlink.threadDemo;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:39 2018/8/14
 */

class MyThread implements Runnable{
    private String title;
    public MyThread(){
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.title + "运行,x = " + i);
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {

        //创建Thread写法
       /* Thread thread1 = new Thread(new MyThread("线程A"));
        Thread thread2 = new Thread(new MyThread("线程B"));
        Thread thread3 = new Thread(new MyThread("线程C"));
        thread1.start();
        thread2.start();
        thread3.start();*/


       //Lambda传统写法
        for (int i = 0; i < 3; i++) {
            String title = "线程对象A-"+ i;
            Runnable run = ()->{
                for (int j = 0; j < 10; j++) {
                    System.out.println(title + " 运行,j= " +j);
                }
            };
            new Thread(run).start();
        }

        //Lambda新写法

        for (int i = 0; i < 3; i++) {
            String title = "线程对象B-"+ i;
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    System.out.println(title + " 运行,j= " +j);
                }
            }).start();
        }
    }
}
