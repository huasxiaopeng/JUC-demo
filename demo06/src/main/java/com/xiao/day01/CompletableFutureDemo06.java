package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo06
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo06 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
         CompletableFuture.supplyAsync(() -> 99999)
                 //需要获取到上面的数据，并提供给消费者
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> "777"), (a, b) -> {
                    System.out.println("a = " + a);
                    System.out.println("b = " + b);
                }).get();
    }
}
