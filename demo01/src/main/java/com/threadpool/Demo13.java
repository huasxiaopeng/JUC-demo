package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号量学习
public class Demo13 {
    //信号量
    static Semaphore semaphore=new Semaphore(3,true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i <50 ; i++) {
              executorService.execute(new Task());
        }
        executorService.shutdown();
    }
    static   class  Task implements Runnable{
        @Override
        public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了凭证可以继续操作");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程--"+Thread.currentThread().getName()+":任务执行完毕");
                semaphore.release(3);
                System.out.println("凭证已经释放，请继续。。。");
            }
    }

}
