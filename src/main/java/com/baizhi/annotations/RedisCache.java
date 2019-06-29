package com.baizhi.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java中注解英文单词   Annotation
 *
 *
 *  1.java 中元注解   元注解: 用来修饰注解的注解称之为元注解
 *
 *
 *   @Retention      作用:注解的保留策略：
 *       @Retention(RetentionPolicy.SOURCE)   // 注解仅存在于源码中，在class字节码文件中不包含
 * 　　  @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
 * 　　  @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 *
 *   @Target         作用: 用来指定注解修饰范围
 *
 *     @Target(ElementType.TYPE)                      // 接口、类、枚举、注解
 *
 * 　　@Target(ElementType.FIELD)                     // 字段、枚举的常量
 *
 * 　　@Target(ElementType.METHOD)                 // 方法
 *
 * 　　@Target(ElementType.PARAMETER)            // 方法参数
 *
 * 　　@Target(ElementType.CONSTRUCTOR)       // 构造函数
 *
 * 　　@Target(ElementType.LOCAL_VARIABLE)   // 局部变量
 *
 * 　　@Target(ElementType.ANNOTATION_TYPE) // 注解
 *
 * 　　@Target(ElementType.PACKAGE)               // 包
 *
 *   @Documented    作用:是否将该注解在javadoc中保留
 *   @Inherited    作用:当前注解是否可以继承
 *
 *
 */

/**
 * 自定义缓存的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RedisCache {




}
