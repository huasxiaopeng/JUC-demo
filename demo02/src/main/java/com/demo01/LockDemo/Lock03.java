package com.demo01.LockDemo;


import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName Lock03
 * @Description 读写锁
 * @Author lktbz
 * @Date 2020/6/11
 */
public class Lock03 {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    //公平的读写方式
//    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    //写锁
    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    //读锁
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();
    /**
     * 分析读写锁
     * api分析：
     * @param args
     */
    public static void main(String[] args) {
//                demo01();
                demo02();
//                  upLockDemo();
                 // downLockDemo();//从上面的例子中发现，升级不可以，降级可以
    }
    /**
     * 既然 synchronized 锁升级策略，读写锁有升级策略么？
     */


    /**
     *  非公平的读写方式
     *  读写混写
     *  在生成子线程去抢
     *
     *
     * 饥饿问题
     */
    private  static  void demo02(){
        new Thread(()->read(),"t1").start();
        new Thread(()->write(),"t2").start();
        new Thread(()->read(),"t3").start();
        new Thread(()->read(),"t4").start();
        new Thread(()->write(),"t5").start();
        new Thread(()->write(),"t6").start();
        new Thread(()->write(),"t7").start();
        //创建子线程去抢
        new Thread(()->
        {
            Thread thread[] = new Thread[1000];
            for (int i = 0; i <1000 ; i++) {
                thread[i]=new Thread(()->read(),"子线程————》read："+i);
            }
            for (int i = 0; i <1000 ; i++) {
                thread[i].start();
            }
        }).start();
        //子线程去写
        new Thread(()->
        {
            Thread thread[] = new Thread[100];
            for (int i = 0; i <100 ; i++) {
                thread[i]=new Thread(()->write(),"子线程-->write："+i);
            }
            for (int i = 0; i <100 ; i++) {
                thread[i].start();
            }
        }).start();
    }
    /**
     * 读写锁demo
     */
    private  static void  demo01(){
        //创建四个线程调用
//        new Thread(()->read(),"t1").start();
//        new Thread(()->read(),"t2").start();
//        new Thread(()->write(),"t3").start();
//        new Thread(()->write(),"t4").start();
    }
    public  static void read(){

        System.out.println("线程"+Thread.currentThread().getName()+"准备获取读锁");
        readLock.lock();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"获取到了读锁");
            Thread.sleep(30);

        } catch (InterruptedException e) {
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+"释放到了读锁");
            readLock.unlock();
        }
    }
    public  static void write(){
            System.out.println("线程"+Thread.currentThread().getName()+"准备获取写锁");
           writeLock.lock();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"获取到了写锁");
            Thread.sleep(50);
        } catch (InterruptedException e) {
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+"释放到了写锁");
            writeLock.unlock();
        }
    }

    /**
     * 读锁升级
     */
    public static  void  upLockDemo(){
        System.out.println("线程"+Thread.currentThread().getName()+"尝试获取读锁");
        readLock.lock();
        try {
        System.out.println("线程"+Thread.currentThread().getName()+"尝试读锁成功");
        Thread.sleep(200);
        System.out.println("线程"+Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        System.out.println("获取写锁成功了，，，，"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("准备释放锁");
            //锁的释放
            writeLock.unlock();
            readLock.unlock();
        }
    }

    /**
     * 写锁降级
     */
    public static  void  downLockDemo(){
        System.out.println("线程"+Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"尝试写锁成功");
            Thread.sleep(200);
            System.out.println("线程"+Thread.currentThread().getName()+"尝试获取读锁");
            readLock.lock();
            System.out.println("获取写锁成功了，，，，"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("准备释放锁了。。。。。。");
            //锁的释放
            readLock.unlock();
            writeLock.unlock();
        }
    }
}
