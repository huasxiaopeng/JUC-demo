package com.xiao.day02;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName Demo01
 * @Description
 * @Author lktbz
 * @Date 2020/7/1
 */
public class Demo01 {
    /**
     * 创建一个完成的CompletableFuture
     */
    @Test
    public   void test01(){
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("message");
        assertTrue(completableFuture.isDone());
        assertEquals("message",completableFuture.getNow(null));
    }

    @Test
    public void test02(){

    }

}
