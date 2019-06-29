package com.baizhi;

import redis.clients.jedis.Jedis;

public class TestJedis {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.186.135",6379);
        jedis.set("name","张三");




    }
}
