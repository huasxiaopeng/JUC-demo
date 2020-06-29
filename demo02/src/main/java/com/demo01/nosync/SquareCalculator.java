package com.demo01.nosync;

import java.util.concurrent.*;

/**
 * @ClassName SquareCalculator
 * @Description 02
 * @Author lktbz
 * @Date 2020/6/21
 */
public class SquareCalculator {
    private static  ExecutorService executorService= Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //isDoneAndGet();
        isDoneAndGetTime();
        isClose(executorService);
    }
    public Future<Integer>calculate(Integer input){
        return  executorService.submit(()->{
                System.out.println("现在正在执行任务"+Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("现在执行任务完毕"+Thread.currentThread().getName());
                return input*input;
        });
    }

    /**
     * 超时时间试获取
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private  static  void isDoneAndGetTime() throws ExecutionException, InterruptedException {
        Future<Integer> calculate = new SquareCalculator().calculate(150);
        while (!calculate.isDone()){
            try {
                System.out.println("Calculating...");
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer integer = null;//阻塞一直等待
        try {
            integer = calculate.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("超时了哟，没有获取到结果？");
        }
        System.out.println(integer);
    }



    /**
     * 阻塞是的获取任务
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private  static  void isDoneAndGet() throws ExecutionException, InterruptedException {
        Future<Integer> calculate = new SquareCalculator().calculate(100);
        while (!calculate.isDone()){
            System.out.println("任务正在运行中。。。。。。");
            try {
                Thread.sleep(5000);
            System.out.println("任务执行完毕即将返回。。。。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer integer = calculate.get();//阻塞一直等待
        System.out.println(integer);
    }
    private  static void isClose(ExecutorService service){
        service.shutdown();
    }
}
