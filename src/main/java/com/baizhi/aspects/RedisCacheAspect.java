package com.baizhi.aspects;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.Jedis;

/**
 * 定义缓存的切面类
 */
@Component
@Aspect
public class RedisCacheAspect {

    @Autowired
    private Jedis jedis;

    //切入点表达式:  只切类中方法上存在相关注解的切入点表达式
    //execution(* com.baizhi.service.*.*(..)) 方法级别切入点
    //within(com.baizhi.service.*ServiceImpl) 类切点表达式
    @Around("@annotation(com.baizhi.annotations.RedisCache)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //redis中是否存在key
        System.out.println("进入切面......");
        //生成业务模块的key
        String mapKey = getMapKey(proceedingJoinPoint);
        //生成当前业务模块查询方法的key
        String key = getKey(proceedingJoinPoint);
        try {
            Boolean exists = jedis.hexists(mapKey,key);
            if (exists){//存在redis有缓存
                String json = jedis.hget(mapKey,key);
                MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
                Class returnType = signature.getReturnType();
                return JSONObject.parseObject(json,returnType);
            }else{
                Object proceed = proceedingJoinPoint.proceed();//放行方法执行
                String json = JSONObject.toJSONString(proceed);
                jedis.hset(mapKey,key,json);//hash hset
                return proceed;
            }
        } catch (Throwable throwable) {
            return proceedingJoinPoint.proceed();
        }finally {
            if (jedis!=null){
                jedis.close();
            }
        }

    }


    /***
     * MD5 算法
     *  java 用来加密
     *  md5  签名  校验核    a.txt   b.txt
     *  特点:  1.无论 什么内容  经过md5算法生成结果是一个长度为32位16进制字符串
     *        2. 相同内容的文件 字符串   每次经过md5 生成结果一致
     *        3. 算法不可逆    (md5 在线解密(穷举))
     *
     *        "123456".md5   xxxx
     *        "123456".md    5a2fec98a258ff31ab2f42fb71d1accdd
     *        md5  888888 + "AABB" 盐
     *
     *        登录: 888888 +"AABB"
     *
     *
     *
     */
    //生成业务模块中缓存方法的key   对生成方法的key 做Md5
    public String getKey(ProceedingJoinPoint proceedingJoinPoint){
        StringBuilder sb  = new StringBuilder();
        sb.append(proceedingJoinPoint.getSignature().getName());
        Object[] args = proceedingJoinPoint.getArgs();
        if(args !=null){
            for (Object o : args) {
                sb.append("*").append(o);
            }
        }
        String key  = sb.toString();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(key.getBytes());//转为16进制字符串
        return md5DigestAsHex;
    }

    //用来生成最外层呢过业务模块的key
    public String getMapKey(ProceedingJoinPoint proceedingJoinPoint){
        StringBuilder sb  = new StringBuilder();
        sb.append(proceedingJoinPoint.getTarget().getClass().getName());
        return sb.toString();
    }



    //清除redis缓存
    @After("@annotation(com.baizhi.annotations.FlushCache)")
    public void After(JoinPoint joinPoint){
        try {
            String name = joinPoint.getTarget().getClass().getName();
            jedis.del(name);
        } finally {
            jedis.close();
        }
    }

}
