package com.baizhi;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestTimer {


    public static void main(String[] args) {




        //创建定时对象  //
        Timer timer = new Timer();  //定时对象中在执行定时任务时原子性  使用时同一个线程   线程安全
        //执行定时任务
        //参数1:定时任务对象  参数二:开启任务的时机    参数3:周期的执行时间 毫秒
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" 任务1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //定时清空数据库中数据

            }
        },new Date(),1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" 任务2");
            }
        },new Date(),1000);  //10分钟 24小时  某个月星期一执行一次

        System.out.println("当前线程名称: "+Thread.currentThread().getName());

    }

}
