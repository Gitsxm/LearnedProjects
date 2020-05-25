package com.example.demo.config;

import com.example.demo.task.QuartzTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(QuartzTask.class).withIdentity("quartzQuartz").storeDurably().build();
    }
    @Bean
    public Trigger testQuartzTrigger(){
        // 简单的时间表配置
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)  //设置时间周期单位秒
                .repeatForever();
        // 需求复杂时使用这种方式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("quartzQuartz")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
