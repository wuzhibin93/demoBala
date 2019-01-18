package com.enlink.time;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:45 2019/1/18
 */

@Configuration
@EnableAsync
public class AsyncConfig {
    /*
        此处的成员变量应该使用@Value从配置中读取
     */
    @Value("${time.corePoolSize}")
    private int corePoolSize;
    @Value("${time.maxPoolSize}")
    private int maxPoolSize;
    @Value("${time.queueCapacity}")
    private int queueCapacity;

    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);

        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
