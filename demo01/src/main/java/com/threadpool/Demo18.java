package com.threadpool;

import java.awt.font.TextHitInfo;
import java.util.concurrent.*;

/**
 * 需求，就是在没有网络的情况，依旧可以播放默认的广告
 */
public class Demo18 {
    //定义默认的广告播放
    private  static  final AD  DEFAILT_AD=new AD("在没有网络的情况下，我就登场了");
    //线程池
    private static final ExecutorService service=Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
     Demo18 demo18=new Demo18();
     demo18.pritAD();

    }
    //定义执行的任务，以及各种处理
    class ADTask implements Callable<AD>{
        @Override
        public AD call() {
            try {
                System.out.println("播放广告倒计时。。。");
                Thread.sleep(3000);

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程在sleep 期间被中断了");
                return new AD("被中断时候的默认广告");
            }
            return new AD("旅游订票哪家强，当然是走哪了。。。");
        }
    }


    //主要处理异常的方法集合
    public  void pritAD(){
        Future<AD> submit = service.submit(new ADTask());
        AD ad = null;
        try {
            submit.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("被中断了e");
            ad=new AD("被中断时候的默认广告");
        } catch (ExecutionException e) {
            e.printStackTrace();
            ad=new AD("执行时候的异常广告");
        } catch (TimeoutException e) {
            e.printStackTrace();
            ad = new AD("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = submit.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }


 static  class AD{
     public AD(String name) {
         this.name = name;
     }

     private  String name;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     @Override
     public String toString() {
         return "AD{" +
                 "name='" + name + '\'' +
                 '}';
     }
 }
}
