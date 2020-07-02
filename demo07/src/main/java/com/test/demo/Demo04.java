package com.test.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Demo04
 * @Description 模拟超时
 * @Author lktbz
 * @Date 2020/7/2
 */
public class Demo04 {
    public static String log;
    private final CountDownLatch latch = new CountDownLatch(1);
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 定义超时规则
    @Test
    public void testSleepForTooLong() throws Exception {
        log += "ran1";
        TimeUnit.SECONDS.sleep(100); // sleep for 100 seconds
    }
    @Test
    public void testBlockForever() throws Exception {
        log += "ran2";
        latch.await(); // will block
    }
}
