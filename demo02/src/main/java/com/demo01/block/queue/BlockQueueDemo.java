package com.demo01.block.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BlockQueueDemo
 * @Description 阻塞队列学习
 * @Author lktbz
 * @Date 2020/7/3
 */
public class BlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue bue=new ArrayBlockingQueue(10);
//        几种添加操作
        bue.add("add");


        bue.offer("offer");

        try {
            bue.put("put");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //获取数据
        try {
            /**
             *   * Retrieves and removes the head of this queue, waiting if necessary
             *      * until an element becomes available.
             *      *
             *      * @return the head of this queue
             *      * @throws InterruptedException if interrupted while waiting
             *      根据api介绍，没有数据会一直等待，
             */
            Object take = bue.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            /**
             * 设置等待时间的
             */
            Object poll = bue.poll(2l, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Object peek = bue.peek();


    }

}
