package com.demo01.threadpooldemo;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * @ClassName ThreadPoolDemo01
 * @Description 创建线程池
 * @Author lktbz
 * @Date 2020/6/14
 */
public class ThreadPoolDemo01 {
    public static void main(String[] args) {
        /**
         * 创建线程池的N种方式
         */
        /**
         * 这是创建一个只有一个队列大小的线程池
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 创建一个无界的链式队列
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(1);
        /**
         * 任务执行
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        /**
         * 创建一个单线程的数组
         */
        ExecutorService executorService2 = newSingleThreadExecutor();
        /**
         * 单线程的任务调度队列
         */
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        /**
         *  创建一个任务窃取线程
         */
        ExecutorService executorService3 = Executors.newWorkStealingPool();
        /**
         *  创建一个回调
         */
        Callable<Object> callable = Executors.callable(() -> System.out.println("a"));
        /**
         *   默认的线程池工厂
         */
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        /**
         * 创建一个特殊的回调
         */
        Callable<Object> objectCallable = Executors.privilegedCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        Executors.privilegedCallableUsingCurrentClassLoader(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        ThreadFactory threadFactory1 = Executors.privilegedThreadFactory();
        ExecutorService executorService5 = Executors.unconfigurableExecutorService(newSingleThreadExecutor());
        ScheduledExecutorService scheduledExecutorService2;
//        scheduledExecutorService2 = Executors.unconfigurableScheduledExecutorService(newScheduledThreadPool(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 2;
//            }
//        }));
    }
}
