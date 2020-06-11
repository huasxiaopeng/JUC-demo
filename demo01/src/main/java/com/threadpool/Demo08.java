package com.threadpool;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  原子引用。对象加，取消锁
 */
public class Demo08 {
    private   AtomicReference<Thread>atomicThread=new AtomicReference<>();
    //加锁
    public  void lock(){
        Thread thread = Thread.currentThread();
        while (!atomicThread.compareAndSet(thread,null)){
            System.out.println("获取自旋锁失败。。。");
        }
    }
    //解锁
    public  void  unlock(){
        Thread current = Thread.currentThread();
        atomicThread.compareAndSet(null,current);
    }

    public static void main(String[] args) {
        Demo08 demo08=new Demo08();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                demo08.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    demo08.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
