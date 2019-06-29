package com.baizhi;


import org.junit.Test;

public class TestSms {


    @Test
    public  void test() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            //参数1: 传递参数   //参数2:请求地址
            String result = Send.SMS("account=xiaochen2018&password=cx521600&mobile=18519336574&content=尊敬的用户您已经注册成功，用户名：【xiaohei】密码：【989776】感谢您的注册！", "http://sms.106jiekou.com/utf8/sms.aspx");
            System.out.println(result);
            Thread.sleep(3000);
        }

    }

}
