package com.spring.reflection.demo;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName Lktbz
 * @Description TODO
 * @Author lktbz
 * @Date 2020/6/28
 */
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Lktbz {

     String value() default "zk";
}
