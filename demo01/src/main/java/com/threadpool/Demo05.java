package com.threadpool;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 递归调用重入
 */
public class Demo05 {
    static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        new Demo05().reverse();
    }
    public static void reverse(){
        lock.lock();
        try {
            System.out.println("我正在解析文件中。。。。。");
            if(lock.getHoldCount()<5){
                System.out.println("前：->重入锁次数为："+lock.getHoldCount());
                reverse();
                System.out.println("后：->重入锁次数为："+lock.getHoldCount());
            }
        }finally {
            lock.unlock();
        }
    }
}
