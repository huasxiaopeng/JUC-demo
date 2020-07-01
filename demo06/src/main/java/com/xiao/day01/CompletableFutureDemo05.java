package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo05
 * @Description 消费
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo05 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> 99999)
                .thenAccept(System.out::println).get();

    }
}
