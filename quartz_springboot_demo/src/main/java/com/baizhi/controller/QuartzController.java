package com.baizhi.controller;

import com.baizhi.job.MyJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

//@RestController比@COntroller多了个@ResponseBody这个注解，他们的区别其实就是@ResponseBody的特性，@ResponseBody这个注解会让所有返回的对象转成json格式，不会返回视图

@RestController
public class QuartzController {
    @Autowired
    private Scheduler scheduler;

    /**
     * 新增调度任务方法
     */
    @RequestMapping("/add")
    public String addJob(String jobName,String jobGroup,String triggerName,String tiggerGroup,String cronExpression){

        try {
            JobDetail jobDetail = newJob(MyJob.class).withIdentity(jobName,jobGroup).build();

            Trigger trigger = newTrigger().withIdentity(triggerName,tiggerGroup).withSchedule(cronSchedule(cronExpression)).build();

            scheduler.scheduleJob(jobDetail,trigger);

            return "success";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 暂定调度任务
     */
    @RequestMapping("/pause")
    public String pauseJob(String jobName,String jobGroup){
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName,jobGroup));
            return "success";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/resume")
    public String resumeJob(String jobName,String jobGroup){
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName,jobGroup));
            return "success";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
