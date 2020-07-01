package com.xiao.day01;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo01
 * @Description 1-10中全部的例子都是使用的fork join 没有用到线程池
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 通过方法，发现创建方式
         */
//        CompletableFuture<String> completableFuture=new CompletableFuture<String>();
//          两种创建方式  待返回结果异步
//        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync();
            //不带返回结果，异步
//        CompletableFuture<Void> future = CompletableFuture.runAsync();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("这是不返回的值"));
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "666666666");



        Optional<CompletableFuture<Void>> future1 = Optional.of(future);
        boolean present = future1.isPresent();
        if(present){
            System.out.println("返回的是个空值");
        }
        String s = completableFuture.get();
        if(!s.isEmpty()){
            System.out.println("拿到了任务返回的结果-->"+s);
        }
    }
}
