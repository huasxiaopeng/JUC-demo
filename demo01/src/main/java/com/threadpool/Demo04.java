package com.threadpool;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用trylock 来避免死锁
 */
public class Demo04  implements  Runnable{
    /**
     * 两把锁
     */
    private  static Lock lock1=new ReentrantLock();
    private  static Lock lock2=new ReentrantLock();
//  两个线程走的逻辑
   int values=1;

    public static void main(String[] args) {
        Demo04 d1 = new Demo04();
        Demo04 d2 = new Demo04();
        d1.values=0;
        d2.values=1;
        new Thread(d1).start();
        new Thread(d2).start();
    }
   //获取锁的例子
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if (values == 0) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MICROSECONDS)) {
                        try {
                            System.out.println("线程1获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));
                            /**
                             * 线程一获取到锁2，代表着锁2即将释放
                             */
                            if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程1获得了锁2");
                                    System.out.println("线程一同事拿到了两把锁");

                                    break;
                                }finally {
                                    lock2.unlock();
                                }
                            }else {
                                System.out.println("线程一获取锁2失败，正在重试");
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程一获取锁1失败，正在重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (values==1){
                try {
                    if(lock2.tryLock(1000,TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("线程2获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));
                            if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println("线程2获得了锁1");
                                    System.out.println("线程2同时拿到了两把锁");
                                    break;
                                }finally {
                                    lock1.unlock();
                                }
                            }else {
                                System.out.println("线程2获取锁1失败，正在重试");
                            }
                        }finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程2获取锁2失败，正在重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


    }}
