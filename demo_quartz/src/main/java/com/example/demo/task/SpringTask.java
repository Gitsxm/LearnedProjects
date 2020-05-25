package com.example.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpringTask {
    @Scheduled(cron = "*/5 * * * * *")
    public void taskRun(){
        System.out.println("Spring task run ——" + new Date());
    }
}
