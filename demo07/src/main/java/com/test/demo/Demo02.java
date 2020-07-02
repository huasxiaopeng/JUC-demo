package com.test.demo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @ClassName Demo02
 * @Description 执行顺序
 * @Author lktbz
 * @Date 2020/7/2
 */
@FixMethodOrder(MethodSorters.JVM)
public class Demo02 {
    @Test
    public void testA() {
        System.out.println("first");
    }
    @Test
    public void testB() {
        System.out.println("second");
    }
    @Test
    public void testC() {
        System.out.println("third");
    }
}
