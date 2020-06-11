package com.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * countDownLatch 用法2 多等一
 * 5个运动员，等待着一个发号员的一声令下，进行出发
 * 现在在加一个统计全部跑完的裁判
 *
 *
 */
public class Demo12 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start=new CountDownLatch(1);
        //这是用于统计结束的
        CountDownLatch end =new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i <5 ; i++) {
            final int no=i+1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("运动员" + no+"号，准备完毕，等待着发令枪");
                    try {
                        start.await();
                        System.out.println("运动员" + no+"号，听到枪响起跑了");
                        TimeUnit.SECONDS.sleep(10);
                        System.out.println("运动员" + no+"号，到达终点。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        //相当于，跑完一个，按一下计步器
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        //发令员检查各种设备准备打枪
        System.out.println("发令员，正在检查设备，请等待。。。");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("枪响了，准备跑步。。。。");
        start.countDown();
        end.await();
        System.out.println("全部到达终点。。。。");
        executorService.shutdown();

    }
}
