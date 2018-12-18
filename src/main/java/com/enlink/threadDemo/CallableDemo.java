package com.enlink.threadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:41 2018/8/15
 */

class MyThread2 implements Callable<String>{
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("***********线程执行************"+i);
        }
        return "线程执行完毕";
    }
}

public class CallableDemo{
    public static void main(String[] args)throws Exception {
        FutureTask <String> task = new FutureTask<>(new MyThread2());
        new Thread(task).start();
        System.out.println("返回数据:"+task.get());
    }
}