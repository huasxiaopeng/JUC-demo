package com.atomic.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/** executorService.shutdown();
 * @ClassName LongAddrDemo
 * @Description longaddr 替换元子类，来提速
 * @Author lktbz
 * @Date 2020/7/2
 */
public class LongAddrDemo {
    public static void main(String[] args) {
        LongAdder adder=new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Long start=System.currentTimeMillis();
        for (int i = 0; i <1000000; i++) {
            executorService.execute(()->{
                  adder.increment();
            });
        }
        Long end=System.currentTimeMillis();
        System.out.println("消耗的总时间为"+(end-start));
        executorService.shutdown();
    }
}
