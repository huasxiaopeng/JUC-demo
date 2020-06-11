package com.threadpool;

import java.util.concurrent.*;

/**
 * 异常的处理方法
 */
public class Demo17 {
    //线程池调用，以及怎么去处理异常
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(new TaskCall());
        try {
        for (int i = 0; i <10 ; i++) {
            System.out.println("制造延迟，以及各种意外条件");
            TimeUnit.SECONDS.sleep(2);
             }
            System.out.println("任务是否执行完毕了？"+submit.isDone());
            submit.get();
        } catch (InterruptedException e) {
                e.printStackTrace();
            System.out.println("InterruptedException中断异常");
          } catch (ExecutionException e) {
            e.printStackTrace();
            System.out.println("ExecutionExceptionz执行异常");
        }
        executorService.shutdown();
    }


    static  class TaskCall implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            throw  new Exception("我不想执行，就是想抛出异常");
        }
    }
}
