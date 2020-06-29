package com.demo01.nosync;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName FutureTaskDemo
 * @Description TODO
 * @Author lktbz
 * @Date 2020/6/21
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task =new Task();
        FutureTask taskFutureTas=new FutureTask<>(task);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(taskFutureTas);
        Object o = taskFutureTas.get();
        System.out.println(o.toString());
        executorService.shutdown();
    }
     static  class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            int i=new Random().nextInt(10);
            Thread.sleep(1000);
            return i;
        }
    }
}
