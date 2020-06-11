package com.demo01.LockDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Lock02
 * @Description ReentrantLock 重入锁
 * @Author lktbz
 * @Date 2020/6/11
 */
public class Lock02  implements  Runnable{
    private  static  Lock lock=new ReentrantLock();
    /**
     * 学习一个类的三个步骤：
     * 1：组合的成员分析
     * 2：构造函数分析
     * 3：api分析
     * @param args
     */
    /**类成员：
     * FairSync 公平实现
     * NonfairSync 非公平实现
     * Sync、、提供所有同步实现的机制
     * @param args
     */
    /**
     * 构造函数分析：
     *  public ReentrantLock() {
     *         sync = new NonfairSync();
     *  }
     *
     *  public ReentrantLock(boolean fair) {
     *         sync = fair ? new FairSync() : new NonfairSync();
     * }
     * 构造公平与非公平锁
     * @param args
     *
     */
    /**
     * api 分析
     *    public int getHoldCount() {
     *         return sync.getHoldCount();
     *     }
     *     字面理解，获取同步器hold 住的线程数量
     *
     *   protected Thread getOwner() {
     *         return sync.getOwner();
     *     }
     *     //线程的所有者
     *
     *
     *  protected Collection<Thread> getQueuedThreads() {
     *         return sync.getQueuedThreads();
     *     }
     *   获取队列中的线程
     *
     *
     *    public final int getQueueLength() {
     *         return sync.getQueueLength();
     *     }
     *   获取队列的长度
     *
     *
     *    protected Collection<Thread> getWaitingThreads(Condition condition) {
     *         if (condition == null)
     *             throw new NullPointerException();
     *         if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
     *             throw new IllegalArgumentException("not owner");
     *         return sync.getWaitingThreads((AbstractQueuedSynchronizer.ConditionObject)condition);
     *     }
     *  //获取等待中的队列
     *
     *
     *  getWaitQueueLength(Condition condition)
     *  获取等待队列的长度
     *
     *
     *     public final boolean hasQueuedThread(Thread thread) {
     *         return sync.isQueued(thread);
     *     }
     *     判断队列中是否存在
     *
     *
     *  public final boolean hasQueuedThreads() {
     *         return sync.hasQueuedThreads();
     *     }
     *    判断
     *
     *      public boolean hasWaiters(Condition condition) {
     *         if (condition == null)
     *             throw new NullPointerException();
     *         if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
     *             throw new IllegalArgumentException("not owner");
     *         return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject)condition);
     *     }
     *    是否有等待，在队列中
     *
     *  isFair()
     *  是否是公平锁
     *
     *  isHeldByCurrentThread()
     *  是否只有并发线程
     *
     *  isLocked()
     *  是否被锁住
     *
     *  lock()
     *  获取锁
     *
     *
     *  lockInterruptibly()
     *  获取锁，如果锁时中断，则抛出异常
     *
     *  newCondition()
     *  构件新的等待队列
     *
     *  tryLock()
     *  尝试获取锁
     *  tryLock(long timeout, TimeUnit unit)
     *  带超时的尝试获取锁
     *
     *  unlock()
     *  释放锁
     * @param args
     */
    public static void main(String[] args) {
        /**
         *  ReentrantLock  重入锁，对读写都加锁，
         */
        Lock lock1=new ReentrantLock();
        /**
         * demo01 例子，定义了三个线程池，去执行任务。
         */
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        for (int i = 0; i <10 ; i++) {
////            executorService.execute(()->demo01(lock1));
////        }
        /**
         *demo02
         */
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        for (int i = 0; i <10 ; i++) {
//          executorService.execute(()->demo02(lock1));
//        }
        /**
         * DEMO03
         */
        Thread t1 = new Thread(new Lock02());
        Thread t2 = new Thread(new Lock02());
         t1.start();
         t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 给线程加中断标记
         */
        t2.interrupted();

        // executorService.shutdown();
    }

    /**
     * 锁的获取与释放  简单
     * @param lock
     */
    private static void  demo01(Lock lock){
        try {
                lock.lock(); //加锁
                //模拟业务逻辑处理
            System.out.println("线程"+Thread.currentThread().getName()+":获取到了锁：：：");
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+":释放到了锁：：：");
                lock.unlock(); //释放锁
        }
    }
    /**
     * tryLock demo
     *
     */
    private static void  demo02(Lock lock){
        try {
            //产生过会获取锁，如果20毫秒，没反应，则抛出异常
            lock.tryLock(20,TimeUnit.MILLISECONDS); //加锁
            //模拟业务逻辑处理
            System.out.println("线程"+Thread.currentThread().getName()+":获取到了锁：：：");
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("获取锁超时");
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+":释放到了锁：：：");
            lock.unlock(); //释放锁
        }
    }


    /**
     * 获取锁，如果线程被中断了，直接抛出异常，
     * @param lock
     */
  private  static  void demo03(Lock lock){
      System.out.println(Thread.currentThread().getName()+"尝试获取锁。。。。。");
      try {
          lock.lockInterruptibly();
          try{
              System.out.println(Thread.currentThread().getName()+"获取到了锁。。。。。");
              Thread.sleep(5000);
          }catch (InterruptedException e){
              System.out.println(Thread.currentThread().getName()+"睡眠期间被中断");
          }finally {
              lock.unlock();
              System.out.println(Thread.currentThread().getName()+"释放了锁");
          }
      } catch (InterruptedException e) {
          e.printStackTrace();
          System.out.println(Thread.currentThread().getName()+"获取锁期间被终端了。。。。");
      }
  }

    /**
     * 回调run 方法
     */
    @Override
    public void run() {
        demo03(lock);
    }



}
