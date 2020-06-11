package com.threadpool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁例子
 */
public class Demo06 {
    private static final  ReentrantReadWriteLock rrw =new ReentrantReadWriteLock();
    //读写锁
    private static final Lock readLock  =rrw.readLock();
    //读写锁
    private static final Lock writeLock =rrw.writeLock();
    public static   void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放读锁");
            readLock.unlock();
        }
    }
    public static  void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }
    public static void main(String[] args) {
        new Thread(()->read(),"t1").start();
        new Thread(()->write(),"t2").start();
        new Thread(()->read(),"t3").start();
        new Thread(()->write(),"t4").start();
        new Thread(()->read(),"t4").start();
    }

}
