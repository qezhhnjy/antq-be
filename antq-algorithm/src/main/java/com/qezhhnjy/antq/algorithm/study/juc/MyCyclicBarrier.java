package com.qezhhnjy.antq.algorithm.study.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhaoyangfu
 * @date 2022/2/22-15:43
 */
public class MyCyclicBarrier {

    public static void main(String[] args) {
        /**
         * 定义一个循环屏障，参数1：需要累加的值，参数2 需要执行的方法
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("召唤神龙"));

        for (int i = 0; i < 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(name + "\t 收集到 第" + tempInt + "颗龙珠");
                try {
                    // 先到的被阻塞，等全部线程完成后，才能执行方法
                    cyclicBarrier.await();
                    System.out.println(name + ": finished");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
