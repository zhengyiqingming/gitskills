package com.baizhi.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class MyJob implements Job {
    /**
     * 任务内容方法
     *
     * @param jobExecutionContext 任务执行的上下文对象  可以获取调度任务的上下文信息  + 数据的传递
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // name zs
        Object name = jobExecutionContext.getJobDetail().getJobDataMap().get("name");

        System.out.println("系统的当前时间:"+new Date() + " | "+name);

    }
}
