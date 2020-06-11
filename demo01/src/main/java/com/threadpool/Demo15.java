package com.threadpool;

import java.util.concurrent.*;

/**
 * 带返回值的例子
 */
public class Demo15 {
    public static void main(String[] args) {
       //线程池执行定义的任务
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(new CallBackTasK());

            if (submit.isCancelled()){
                System.out.println("任务呗取消");
            }
            if (!submit.isDone()){
                System.out.println("任务还没有执行完毕。。。。");
            }
            try {
                System.out.println(submit.get());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
         executorService.shutdown();
    }



    static  class CallBackTasK implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            //模拟程序执行
            TimeUnit.SECONDS.sleep(3);
            System.out.println("任务执行完毕，即将返回");
            return Integer.valueOf("6666");
        }
    }
}
