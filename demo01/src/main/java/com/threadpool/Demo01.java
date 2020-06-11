package com.threadpool;

import java.util.concurrent.*;

/**
 * 线程池创建
 * 单个线程
 * 100个线程
 * 10000个线程
  分别怎么去实现
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        //创建单个线程
        /**Thread t=new Thread(new Task());
        t.start();*/
        //for循环创建·1000个线程
      /**  long startTime=System.currentTimeMillis();
        for (int i = 0; i <1000 ; i++) {
          Thread t=new Thread(new Task());
             t.start();
        }
        long endTime=System.currentTimeMillis()-startTime;
        System.out.printf("执行了多少时间：-->"+endTime);**/
        /**
         * 核心线程数是5，最大线程数为10，，队列为100
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long startTime1=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            executorService.execute(()-> System.out.println(Thread.currentThread().getName()));
        }
        long end1=System.currentTimeMillis();
        System.out.println("newFixedThreadPool 总耗时为："+(end1-startTime1));
        //睡两秒
        TimeUnit.SECONDS.sleep(2);
        //单个线程处理
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        long startTime=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            executorService1.execute(()-> System.out.println(Thread.currentThread().getName()));
        }
        long end=System.currentTimeMillis();
        System.out.println("newSingleThreadExecutor 总耗时为："+(end-startTime));
        //睡两秒
        TimeUnit.SECONDS.sleep(2);
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        long startTime2=System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            executorService2.execute(()-> System.out.println(Thread.currentThread().getName()));
        }
        long end2=System.currentTimeMillis();
        System.out.println("newCachedThreadPool 总耗时为："+(end2-startTime2));
        executorService.shutdown();
        executorService1.shutdown();
        executorService2.shutdown();
//        ThreadPoolExecutor threadPoolExecutor =
//                new ThreadPoolExecutor(5, 10, 0l,
//                        TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(100) );

    }
    static  class Task implements Runnable{
        public void run() {
            System.out.println("执行了子任务");
        }
    }
}
