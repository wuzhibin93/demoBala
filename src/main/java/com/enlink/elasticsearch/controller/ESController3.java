package com.enlink.elasticsearch.controller;

import com.enlink.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:35 2018/9/18
 */
@RestController()
@RequestMapping("es3")
public class ESController3 {
    @RequestMapping("insert")
    public void ess(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 40, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        userInsert myTask = new userInsert();
        systemInsert systemInsert = new systemInsert();
        resourceInsert resourceInsert = new resourceInsert();
        adminInsert adminInsert = new adminInsert();
        System.out.println("启动时间："+ DateUtils.datetime2string(new Date()));
        int x = 10;
        for(int i=0;i<x;i++){
            executor.execute(myTask);
            executor.execute(systemInsert);
            executor.execute(resourceInsert);
            executor.execute(adminInsert);

            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

        executor.shutdown();
    }
}
