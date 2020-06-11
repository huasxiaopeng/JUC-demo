package com.threadpool;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 *   把基本类型，变成原子类型
 */
public class Demo09 implements Runnable {
    static BecomeClass tom;
    static BecomeClass peter;

   public   static AtomicIntegerFieldUpdater<BecomeClass>becomeClassAtomicIntegerFieldUpdater=
           AtomicIntegerFieldUpdater.newUpdater(BecomeClass.class,"value");

    @Override
    public void run() {
        for (int i = 0; i <10000 ; i++) {
            tom.value ++;
            becomeClassAtomicIntegerFieldUpdater.getAndIncrement(peter);
        }
    }

    static  class BecomeClass{
        volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        tom=new BecomeClass();
        peter=new BecomeClass();
        Demo09 d9=new Demo09();
        Thread t1 = new Thread(d9);
        Thread t2 = new Thread(d9);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量："+tom.value);
        System.out.println("升级后的结果"+ peter.value);
    }
}
