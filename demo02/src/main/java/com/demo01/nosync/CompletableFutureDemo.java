package com.demo01.nosync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletableFutureDemo
 * @Description 使用java 8来规避掉future 的缺点
 * @Author lktbz
 * @Date 2020/6/21
 *
 *  demo01
 *
 *  场景： 我看电视又渴又饿，现在让我媳妇去给咱买点水，我去拿点吃的，我想这两个同时去执行，而不是分开去执行。
 *
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        demo01();
          demo02();
    }
    private static void demo01(){
        /**
         * 一遍等媳妇买水，一遍去拿吃的
         *
         */
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("老婆，我渴了，给我买水去");
            try {
                System.out.println("媳妇换好衣服，准备出门买水去了");
                Thread.sleep(3000);
                System.out.println("买好水，准备回去");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "农夫山泉";
        }).thenAccept(resu -> {
            System.out.println("准备喝水");
        });
        System.out.println("我去拿点零食");
//          future.join();
        try {
            Void aVoid = future.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    //api 分析
    /**
     * supplyAsync
     * 返回一个带返回值的异步 CompletableFuture<U>
     * CompletableFuture<Void> runAsync
     * 空返回的异步
     *
     */
    private  static void demo02() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("run async 测试...");
//                return "sss"; //不能定义返回
        });
    }
    private static void demo03(){
//        CompletableFuture.supplyAsync(()->{
//            System.out.println("我要去海南玩耍。。。");
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "去玩了";
//        }).thenAccept((s)->{
//            System.out.println("我是一个消费者");
//
//        }).thenApply((s1,s2)->{
//        })
    }
}
