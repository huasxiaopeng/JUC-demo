package com.atomic.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/2
 */
public class Demo01 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(1);
//        //获取值
//        int i1 = atomicInteger.get();
//        System.out.println(i1);
//        //设置新值
//        atomicInteger.set(2);
//        System.out.println(atomicInteger.get());
        //创建三个线程，争相测量值
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 0; i <50 ; i++) {
//            executorService.execute(()->{
//             String name= Thread.currentThread().getName();
//                //添加
//                int i2 = atomicInteger.addAndGet(1);
//                System.out.println("线程"+name+"修改的值为"+i2);
//            });
//        }
//        executorService.shutdown();


        //比较并且添加
        //atomicInteger.compareAndSet(2,3);
//        ExecutorService executorService1= Executors.newFixedThreadPool(10);
//        for (int i = 0; i <20 ; i++) {
//            executorService1.execute(()->{
//                String name= Thread.currentThread().getName();
//                //原子递减
//                int i2 = atomicInteger.decrementAndGet();
//                System.out.println("线程"+name+"自增"+i2);
//            });
//        }
//        executorService1.shutdown();
        //累加获取，通过debug 发现，首先get 获取自增值2传给a，然后将3传给B，最后，2*3
//        int i = atomicInteger.accumulateAndGet(3, (a, b) -> {
//            return a * b;
//        });
//        System.out.println("累加的值"+i);
//        for (int i = 0; i <10 ; i++) {
//            System.out.println("第一个是什么操作？自增"+atomicInteger.incrementAndGet());
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.println("这是什么擦着呢？ 自减"+ atomicInteger.decrementAndGet());
//        }
        /**
         * 插入10w加数据，来求时间
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Long start=System.currentTimeMillis();
        for (int i = 0; i <1000000; i++) {
            executorService.execute(()->{
                int i1 = atomicInteger.incrementAndGet();

            });
        }
        Long end=System.currentTimeMillis();
        System.out.println("消耗的总时间为"+(end-start));
        executorService.shutdown();
    }
}
