package com.demo01.LockDemo;

import java.util.concurrent.locks.Lock;

/**
 * @ClassName Lock01
 * @Description lock 接口使用学习指南
 * @Author lktbz
 * @Date 2020/6/11
 */
public class Lock01 {
    public static void main(String[] args) {
        /**
         * 因为是接口，所以具体实现，查看lock02
         */
        Lock lock = null;
        /**
         * 加锁
         *  void lock();
         */
        /**
         *  加锁，如果锁时中断状态，则会抛出inter异常
         *  void lockInterruptibly() throws InterruptedException;
         */
        /**
         *  尝试获取锁，紧锁时空闲时间
         *  boolean tryLock();
         */
        /**
         * 带有延时的获取锁
         *   boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
         */
        /**
         * 解锁
         * void unlock();
         */
        /**
         * 等待队列
         *  Condition newCondition();
         */
    }


}
