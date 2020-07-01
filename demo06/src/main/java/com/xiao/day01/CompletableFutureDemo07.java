package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletableFutureDemo07
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
            CompletableFuture.supplyAsync(() -> {
            System.out.println("开始执行中");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 99999;
            //同步执行一个线程
        }).thenRun(() -> {
                    System.out.println("执行改结束了");
                }
        ).get();
    }
}
