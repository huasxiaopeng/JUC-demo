package com.demo01.nosync;

import java.util.concurrent.*;

/**
 * @ClassName FutureAndCallableDemo
 * @Description 练习01
 * @Author lktbz
 * @Date 2020/6/21
 */
public class FutureAndCallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Object> submit = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                //睡两秒模拟处理任务，，，
                System.out.println("线程正在执行任务"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程执行完任务"+Thread.currentThread().getName());
                String s = "lktbz";
                return s;
            }
        });
        try {
            System.out.println("线程正在等待获取"+Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            Object o = submit.get();
            System.out.println("线程拿到数据"+Thread.currentThread().getName());
            System.out.println(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
