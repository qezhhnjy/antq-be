package com.qezhhnjy.antq.algorithm.study.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhaoyangfu
 * @date 2022/2/22-15:42
 */
public class MyCountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门");
    }
}
