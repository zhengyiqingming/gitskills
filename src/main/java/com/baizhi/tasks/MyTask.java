package com.baizhi.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用来测试定时任务类
 */
@Component
@EnableScheduling   //作用: 用在类上  代表这个类是一个定时任务类
public class MyTask {





    @Scheduled(cron = "1-30 10-15 0,2,13,20,23 1,24,31 6,12 4 ")//指定任务的开始时间或周期执行时间  //每秒执行一次* * * * * ?
    // 秒   分   时  日  月  周  年
    public void  task(){
        System.out.println(Thread.currentThread().getName()+"定时任务的内容....");

    }



    /*@Scheduled(cron = "* * * * * ?")
    public void task1(){
        System.out.println(Thread.currentThread().getName()+"hello world");
    }*/

    /**
     * 线程池作用:
     *      1.用来控制系统中创建线程的数量   线程有限
     *      2.节省每次创建线程时系统开销
     */


}
