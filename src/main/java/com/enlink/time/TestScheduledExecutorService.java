package com.enlink.time;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(基于线程池设计的定时任务，每个调度任务都会分配到线程池中的一个线程去执行，任务是并发进行，互不影响)
 * @Date : Created in 15:20 2019/1/18
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //参数1、任务体，2、首次执行时间，3、任务执行间隔，4、间隔时间单位
        //scheduledExecutorService.scheduleAtFixedRate(()-> System.out.println("task ScheduledExecutorService" + new Date()),0,3, TimeUnit.SECONDS);
    }
}
