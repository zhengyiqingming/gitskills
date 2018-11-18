package com.baizhi.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzSpringTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
        applicationContext.start();
    }
}
