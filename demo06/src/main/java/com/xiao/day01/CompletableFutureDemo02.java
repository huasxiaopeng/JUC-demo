package com.xiao.day01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CompletableFutureDemo02
 * @Description 处理计算
 * @Author lktbz
 * @Date 2020/7/1
 */
public class CompletableFutureDemo02 {
    public static void main(String[] args) {
//        demo01();
         demo02();

    }


    /**
     * 正常的运算逻辑
     */
    private static void  demo01(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程--" + Thread.currentThread().getName() + "正在运行");
            System.out.println("开始模拟运算");
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务执行结束。。。");

            return 2333;
        });
        /**
         * 任务执行完毕，接着继续
         */
        try {
            Integer result = future.whenComplete((a, b) -> {
                System.out.println("结果为" + a);
                System.out.println("exception" + b);
            }).exceptionally(e -> {
                System.out.println(e.getMessage());
                return 666;
            }).get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
    /**
     * 抛出异常
     */
    private static void demo02(){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程--" + Thread.currentThread().getName() + "正在运行");
            System.out.println("开始模拟运算");
            try {
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a=1/0; //出错
            System.out.println("任务执行结束。。。");

            return 2333;
        });
        /**
         * 任务执行完毕，接着继续
         */
        try {
            Integer result = future.whenComplete((a, b) -> {
                System.out.println("结果为" + a);
                System.out.println("报错我就有值了啊 " + b);
            }).exceptionally(e -> {
                System.out.println("运行出错了。。。"+e.getMessage());
                return 666;
            }).get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
