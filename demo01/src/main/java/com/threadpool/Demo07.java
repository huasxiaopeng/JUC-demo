package com.threadpool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  读写锁例子，竞争
 */
public class Demo07 {
    private static final  ReentrantReadWriteLock rrw =new ReentrantReadWriteLock(true);
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
        new Thread(()->write(),"Thread1").start();
        new Thread(()->read(),"Thread2").start();
        new Thread(()->read(),"Thread3").start();
        new Thread(()->write(),"Thread4").start();
        new Thread(()->read(),"Thread5").start();
        //在创建子线程，去做竞争
        new Thread(new Runnable() {
            @Override
            public void run() {
              Thread [] thread=new Thread[1000];
                for (int i = 0; i <1000 ; i++) {
                    thread[i]=new Thread(()->read(),"子线程创建的线程"+i);
                }
                for (int i = 0; i <1000 ; i++) {
                   thread[i].start();
                }
            }
        }).start();
    }

}
