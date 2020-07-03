package com.demo01.block.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName SysBlockDemo
 * @Description 只有一个大小的队列，put 进去必须仅需消费，不然会被阻塞
 * @Author lktbz
 * @Date 2020/7/3
 */
public class SysBlockDemo {
    public static void main(String[] args) {
        final SynchronousQueue<Integer> queue=new SynchronousQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for ( AtomicInteger integer = new AtomicInteger(1); integer.get()<100; integer.incrementAndGet())
        {
            /**
             * 下面这个demo 是新式死锁方式。。。
             */
            executorService.execute(new Runnable() {
                public void run() { //写线程
//                    try {
//                        queue.put(integer.get());
//                        queue.add(integer.get());
                         queue.offer(integer.get());//会发生死锁
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            });
           executorService.execute(()->{
               try {
                   System.out.println("读线程-。获取的值"+queue.take());

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           });
        }
        executorService.shutdown();

    }
}
