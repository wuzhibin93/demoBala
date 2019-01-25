package com.enlink.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(定时任务，只能以一定的频率执行任务，无法使用固定时间进行任务)
 * @Date : Created in 15:17 2019/1/18
 */
public class TestTimer {
    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task run:" + new Date());
            }
        };
        Timer timer = new Timer();
        //timer.schedule(timerTask,10,3000);
    }
}
