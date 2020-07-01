package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo03
 * @Description 结果转换
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s = CompletableFuture
                .supplyAsync(() -> 2222)
                .thenApply(String::valueOf)
                .get();
        System.out.println("转换后的值为"+s);
    }
}
