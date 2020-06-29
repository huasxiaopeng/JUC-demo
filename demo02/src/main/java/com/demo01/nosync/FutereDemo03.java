package com.demo01.nosync;

import java.util.concurrent.*;

/**
 * @ClassName FutereDemo03
 *               虚构场景
 * @Description  现在假设，下订单操作，需要执行，生成订单-->发送短信告知是否支付--->登录帐号
 * @Author lktbz
 * @Date 2020/6/21
 */
public class FutereDemo03 {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<String> submit = executorService.submit(new orderTask());
        String s = submit.get();
        Future<String> submit1 = executorService.submit(new SMSTask(s));
        String s1 = submit1.get();
        Future<String> submit2 = executorService.submit(new loginTask(s1));
        String s2 = submit2.get();
        System.out.println("生成的登录用户是"+s2);
        executorService.shutdown();
    }

    //生成订单
 static  class  orderTask implements Callable<String>{
     @Override
     public String call() throws Exception {
         String orderNo="ZK1234";
         return orderNo;
     }
 }
 //验证的
 static  class  SMSTask implements  Callable<String>{
        private String order;

     public SMSTask(String order) {
         this.order = order;
     }

     @Override
     public String call() throws Exception {
         String sms="请您发送验证码到。。。。";
         return sms;
     }
 }
 static  class  loginTask implements  Callable<String>{
        private String msg;

     public loginTask(String msg) {
         this.msg = msg;
     }

     @Override
     public String call() throws Exception {
         String login="登录成功";
         return login;
     }
 }
}
