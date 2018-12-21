package com.enlink.threadDemo;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:35 2018/8/15
 */
public class TicketDemo {
    public static void main(String[] args) {
        MyTherad myThread = new MyTherad();
        //第一个线程启动
        new Thread(myThread).start();
        //第二个线程启动
        new Thread(myThread).start();
        //第三个线程启动
        new Thread(myThread).start();
    }
}

class MyTherad implements Runnable{
    //剩余票数
    private int ticket = 5;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(this.ticket > 0) {
                System.out.println("买票,x = " + ticket--);
            }
        }
    }
}
