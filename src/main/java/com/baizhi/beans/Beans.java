package com.baizhi.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@Configuration
public class Beans {



    @Bean
    public Jedis getJedis(){
        return new Jedis("192.168.186.135",6379);
    }


    /**
     * <bean id="" class="">
     *
     *     </bean>
     */

}
