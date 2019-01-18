//package com.enlink.time;
//
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @ClassName :
// * @Author Aprwu
// * @Description : TODO(类的作用)
// * @Date : Created in 16:15 2019/1/18
// */
//@Configuration
//public class QuartzConfig {
//    @Bean
//    public JobDetail teatQuartzDetail(){
//        return JobBuilder.newJob(TestQuartz.class).withIdentity("testQuartz").storeDurably().build();
//    }
//    @Bean
//    public Trigger testQuartzTrigger(){
//        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(10)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
//                .withIdentity("testQuartz")
//                .withSchedule(simpleScheduleBuilder)
//                .build();
//    }
//}
