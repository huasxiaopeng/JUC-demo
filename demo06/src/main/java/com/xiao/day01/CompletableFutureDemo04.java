package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo04
 * @Description 扁平转换 (降维度场景) 比如map<String,<List<Object> map--->Map<String,Object>
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s = CompletableFuture.supplyAsync(() -> 2333)
                .thenCompose(t -> CompletableFuture.supplyAsync(() -> t + "dddd"))
                .get();
        System.out.println(s);
    }
}
