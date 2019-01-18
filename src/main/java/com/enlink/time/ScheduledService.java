package com.enlink.time;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:25 2019/1/18
 */
@Slf4j
@Component
@EnableScheduling
public class ScheduledService {

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        LOGGER.info(" =====>>>>>>使用cron {} ",System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 5000)
    public void scheduled1(){
        LOGGER.info(" =====>>>>>>fixedRate {} ",System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled2(){
        LOGGER.info(" =====>>>>>>fixedDelay {} ",System.currentTimeMillis());
    }
}
