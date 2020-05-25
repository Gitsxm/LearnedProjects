package com.example.demo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class QuartzTask extends QuartzJobBean {
    /**
     * 定时任务方法
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Quartz Task run --：" + new Date());
        //throw new RuntimeException("error");
    }
}
