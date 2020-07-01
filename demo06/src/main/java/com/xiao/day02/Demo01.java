package com.xiao.day02;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName Demo01
 * @Description
 * @Author lktbz
 * @Date 2020/7/1
 */
public class Demo01 {
    /**
     * 创建一个完成的CompletableFuture
     */
    @Test
    public   void test01(){
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("message");
        assertTrue(completableFuture.isDone());
        assertEquals("message",completableFuture.getNow(null));
    }

    @Test
    public void test02(){
        /**CompletableFuture api 剖析
         *
         *
         * 创建 方式
         * public static CompletableFuture<Void>   runAsync(Runnable runnable) 异步没返回
         * public static CompletableFuture<Void>   runAsync(Runnable runnable, Executor executor)
         * public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier) 异步返回
         * public static <U> CompletableFuture<U>  supplyAsync(Supplier<U> supplier, Executor executor)
         */
        /**
         * CompletableFuture 主动计算
         * public T    get()
         * public T    get(long timeout, TimeUnit unit)
         * public T    getNow(T valueIfAbsent)
         * public T    join()
         *
          */
        /**
         *   三个参数，并且返回U
         * public <U> CompletableFuture<U>     handle(BiFunction<? super T,Throwable,? extends U> fn)
         * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
         * public <U> CompletableFuture<U>     handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
         */
        /**thenApply
         *  根据创建返回的值，进一步进行处理
         * public <U> CompletableFuture<U>     thenApply(Function<? super T,? extends U> fn)
         * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn)
         * public <U> CompletableFuture<U>     thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
         */
       /* CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{

            return "hello world";
        });

        CompletableFuture<String> future3 = future.thenApply((element)->{
            return element+"  addPart";
        }).thenApply((element)->{
            return element+"  addTwoPart";
        });
        System.out.println(future3.get());//hello world  addPart  addTwoPart*/
        /**
         * 根据传入的值，进行消费
         * public CompletableFuture<Void>  thenAccept(Consumer<? super T> action)
         * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action)
         * public CompletableFuture<Void>  thenAcceptAsync(Consumer<? super T> action, Executor executor)
         */
        /*CompletableFuture future4 = future2.thenAccept((e)->{
            System.out.println("without return value");
        });
        future4.get();*/
        /**
         * CompletableFuture
         * 计算结果完成时的处理
         public CompletableFuture<T>     whenComplete(BiConsumer<? super T,? super Throwable> action)
         public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
         public CompletableFuture<T>     whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
         public CompletableFuture<T>     exceptionally(Function<Throwable,? extends T> fn)
         */
        //入参为原始的CompletableFuture的结果.

        /**
         * thenAcceptBoth
         * 这个方法用来组合两个CompletableFuture,其中一个CompletableFuture等待另一个CompletableFuture的结果.
         */
       /* CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            return "hello world";
        });
        CompletableFuture future5 =  future.thenAcceptBoth(CompletableFuture.completedFuture("compose"),
                (x, y) -> System.out.println(x+y));//hello world compose */


        /**
         * Either和ALL
         * thenAcceptBoth是当两个CompletableFuture都计算完成，
         * 而我们下面要了解的方法applyToEither是当任意一个CompletableFuture计算完成的时候就会执行。

         */

      /*  Random rand = new Random();
        CompletableFuture<Integer> future9 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> future10 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 200;
        });
        //两个中任意一个计算完成,那么触发Runnable的执行
        CompletableFuture<String> f =  future10.applyToEither(future9,i -> i.toString());
        //两个都计算完成,那么触发Runnable的执行
        CompletableFuture f1 = future10.acceptEither(future9,(e)->{
            System.out.println(e);
        });
        System.out.println(f.get());*/
    }

}
