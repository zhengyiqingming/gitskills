package com.baizhi;

import com.baizhi.job.MyJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {
    public static void main(String[] args) throws SchedulerException, ParseException {
        //1. 获取调度器对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2. 封装任务内容及其描述信息
        JobDetail jobDetail = newJob(MyJob.class).withIdentity("myJob","g1").usingJobData("name","zs").build();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = dateFormat.parse("2018-11-09 14:46:30");

        //3. 封装触发信息及其描述信息
        Trigger trigger = newTrigger().withIdentity("myTrigger","g1")
                //.startNow()
                .startAt(date)
                // 定义详细的触发规则  简单的触发规则: 每隔5秒 用用重复执行调度任务
                //.withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                //.withSchedule(simpleSchedule().withIntervalInHours(2).repeatForever())

                .withSchedule(cronSchedule("33 51 14 ? * 6 2018")) // 每周五的早上10点50分00秒 触发调度任务
               // .withSchedule(cronSchedule("0/5 * * * * ? *")) // 每周五的早上10点50分00秒 触发调度任务
                .build();
        //4. 通过调度器调度管理任务
        scheduler.scheduleJob(jobDetail,trigger);

        //5. 启用调度器
        scheduler.start();
    }
}
