package com.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * condownLatch Demo
 */
public class Demo10 {
    //生产出的产品，需要等待5个人检查完毕才能进行下一步骤，  1等多
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <5 ; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("工人"+Thread.currentThread().getName()+"正在检查中");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println("工人线程"+Thread.currentThread().getName()+"完成了检查。。。。");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }finally {
                        latch.countDown();
                    }
                }
            };
            //提交任务
            executorService.submit(runnable);
        }
        System.out.println("等待5个人检查完");
        latch.await();
        System.out.println("所有人检查完毕，准备进入下一道工序");
    }
}
