package com.baizhi.config;

import com.baizhi.job.MyJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean //创建Scheduler---->相当于<bean> Scheduler
    public SchedulerFactoryBean createSSF(CronTrigger cronTrigger){
        SchedulerFactoryBean ssf = new SchedulerFactoryBean();
        ssf.setTriggers(cronTrigger);
       // ssf.setConfigLocation(new ClassPathResource("classpath:com/baizhi/quartz.properties"));
        return  ssf;
    }

    @Bean //创建Job--->相当于<bean> Job
    public JobDetailFactoryBean createJFB(){
        JobDetailFactoryBean jfb = new JobDetailFactoryBean();
        jfb.setJobClass(MyJob.class);
        jfb.setName("myJob");
        jfb.setGroup("g1");
        return jfb;
    }

    @Bean //创建Trigger--->相当于<bean> Trigger
    public CronTriggerFactoryBean createCFB(JobDetail jobDetail){
        CronTriggerFactoryBean cfb=new CronTriggerFactoryBean();
        cfb.setName("myTrigger");
        cfb.setGroup("g1");
        cfb.setCronExpression("0/5 * * * * ? *");
        cfb.setJobDetail(jobDetail);
        return cfb;
    }
}
