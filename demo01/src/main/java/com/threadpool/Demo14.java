package com.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//condition 练习
public class Demo14 {
      private ReentrantLock locks= new ReentrantLock();
      private Condition condition= locks.newCondition();

      void producer(){
          locks.lock();
          try{
              System.out.println("条件不满足，开始等待。。。。");
              condition.await();
              System.out.println("条件满足了，开始执行。。。。");
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              locks.unlock();
          }
      }
      void customer(){
            locks.lock();
            try {
                System.out.println("准备工作已经弄好，准备唤醒");
                condition.signalAll();
            }finally {
                locks.unlock();
            }
      }
    public static void main(String[] args) {
        Demo14 demo14=new Demo14();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    demo14.customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        demo14.producer();
    }
}
