package com.baizhi;

public class HelloServiceImpl implements HelloService{

    public String sayHello(String name) {
        System.out.println("接收到的请求参数:"+name);
        return "Hello:"+name;
    }
}
