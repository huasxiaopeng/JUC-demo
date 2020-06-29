package com.demo01.LockDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * @ClassName Lock04
 * @Description StampedLock 使用 邮票锁 特点:读锁，写锁，乐观锁。
 * @Author lktbz
 * @Date 2020/6/12
 */
public class Lock04 {
    private static final StampedLock sl = new StampedLock();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
            // demo1(sl,executorService);
              demo02(sl,executorService);

   }


    /**
     * 悲观读
     * @param newX
     * @param newY
     */
    void moveIfAtOrigin(double newX, double newY) { // upgrade
        // Could instead start with optimistic, not read mode
        double x=0.0,y=0.0;
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }
                else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlock(stamp);
        }
    }
    /**
     * 乐观读
     */
      private static void demo02(StampedLock lock ,ExecutorService service ){
          for (int i = 0; i <5 ; i++) {
              optimisticLockRead(lock);
          }
          for (int i = 0; i <100 ; i++) {
              new Thread(()->optimisticLockRead(lock),"子线程"+i).start();
          }
          service.shutdown();

      }
    private static  void  optimisticLockRead(StampedLock lock){
        long l = lock.tryOptimisticRead();
       //此处加个读的实例
        if(!lock.validate(l)){  //检查乐观锁读后面是够有其他写锁发生
            System.out.println(Thread.currentThread().getName()+"准备获取读锁。。。");
            long l1 = lock.readLock();
            System.out.println(Thread.currentThread().getName()+"读锁。。。获取成功");
            try{
              Thread.sleep(30);

            System.out.println(Thread.currentThread().getName()+"任务执行完毕，准备释放读锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
                lock.unlock(l1);
            }
        }else{
            System.out.println("-------------------------");
        }
    }
    /**
     * 读写锁例子
     * 简单例子
     * @param lock
     */
    private static void demo1(StampedLock lock ,ExecutorService service ){
        for (int i = 0; i <3 ; i++) {
//           service.execute(()->read(lock));
           service.execute(()->write(lock));
//           service.execute(()->read(lock));
        }
        service.shutdown();

    }
    private  static void read(StampedLock lock){
        long l = lock.readLock();
        System.out.println("线程"+Thread.currentThread().getName()+"-->准备获取读锁");
        try {
            Thread.sleep(2000);
        System.out.println("线程"+Thread.currentThread().getName()+"-->准备拿到读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        System.out.println("线程"+Thread.currentThread().getName()+"-->准备释放读锁");
            lock.unlockRead(l);
        }
    }
    private  static void write(StampedLock lock){
        long l = lock.writeLock();
        System.out.println("线程"+Thread.currentThread().getName()+"-->准备获取写锁");
        try {
            Thread.sleep(2000);
            System.out.println("线程"+Thread.currentThread().getName()+"-->准备拿到写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+"-->准备释放写锁");
            lock.unlockWrite(l);
        }
    }

    class Point {
        private double x, y;
        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }
        double distanceFromOrigin() { // A read-only method
            long stamp = sl.tryOptimisticRead();
            double currentX = x, currentY = y;
            if (!sl.validate(stamp)) {
                stamp = sl.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
    }
}
