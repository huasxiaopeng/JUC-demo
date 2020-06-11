package com.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 扩展线程池功能，需求描述，希望，在线程执行过程中，前后增加暂停与唤醒功能，
 */
public class Demo02 extends ThreadPoolExecutor {
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    /**
     * 判断是否停止标志
     */
    private boolean isStop;

    public Demo02(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public Demo02(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public Demo02(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public Demo02(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        try {
            lock.lock();
            if(isStop){//如果为真。就挂起线程
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //暂停
     private  void puse(){
        lock.lock();
        try {
            isStop=true;
        }finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            isStop = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo02 d=new Demo02(10,20,0l,TimeUnit.SECONDS,new LinkedBlockingDeque());
        //循环10000次
        for (int i = 0; i <10000 ; i++) {
            d.execute(()->{
                System.out.println("Thread-->"+Thread.currentThread().getName()+"我被执行了。。。");
            });

        }
        Thread.sleep(2000);
        d.puse();
        System.out.println("我设置了暂停。。。");
        Thread.sleep(2000);
        d.resume();
        System.out.println("现在又自动恢复了。。。。。。。");
        d.shutdown();
    }

}
