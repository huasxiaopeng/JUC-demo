package com.threadpool;

import java.util.concurrent.*;


/**
 * lambda 方式实现callable
 */
public class Demo16 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer>ins=()->{
            System.out.println("正在处理任务");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("任务处理完毕");
            return Integer.valueOf("6666");
        };
        Future<Integer> submit = executorService.submit(ins);
        Integer integer = submit.get();
        System.out.println("返回的值为："+integer);
        executorService.shutdown();
    }
}
