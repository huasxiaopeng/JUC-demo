package com.threadpool;

import java.text.SimpleDateFormat;

/**
 * ThreadLocal demo
 */
public class Demo03 {
//思路，就是创建一个th


    static class SimToDate{
       private static  ThreadLocal<SimpleDateFormat>smf =new ThreadLocal<SimpleDateFormat>(){
           @Override
           protected SimpleDateFormat initialValue() {
               return new SimpleDateFormat("YYYY-MM-DD");
           }
       };
    }



}


