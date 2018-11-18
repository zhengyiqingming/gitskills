package com.baizhi.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SpringTaskConfig {
    //调度任务:任务内容(方法代码体)+触发时机(cron表达式)
    @Scheduled(cron="0/5 * * * * ?")// 注意:只支持6为的cron表达式 不支持年
    public void m1(){
        System.out.println(new Date());
    }

}
