package com.qezhhnjy.antq.algorithm.study.juc;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaoyangfu
 * @date 2022/2/7-18:27
 */
@Slf4j
public class MyLock {

    //
    private static final ReentrantLock LOCK      = new ReentrantLock();
    private static final Condition     A         = LOCK.newCondition();
    private static final Condition     B         = LOCK.newCondition();
    private static final Condition     C         = LOCK.newCondition();
    private static final Condition     CONDITION = LOCK.newCondition();

    public static int flag = 1;

    public static void main(String[] args) {
        new Thread(() -> print(CONDITION, CONDITION, "A", 1), "t-a").start();
        new Thread(() -> print(CONDITION, CONDITION, "CCC", 3), "t-c").start();
        new Thread(() -> print(CONDITION, CONDITION, "BB", 2), "t-b").start();
        ThreadUtil.sleep(1000);
    }

    public static void print(Condition condition, Condition next, String print, int mark) {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 20) {
            try {
                LOCK.lock();
                // 使用while防止虚假唤醒
                while (flag != mark) condition.await();
                log.info(print);
                // ThreadUtil.sleep(500);
                if (flag == 3) flag = 1;
                else flag++;
                next.signal();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                LOCK.unlock();
                count++;
            }
        }
    }
}

class ProducerAndConsumer {
    private static final ReentrantLock LOCK      = new ReentrantLock();
    private static final Condition     CONDITION = LOCK.newCondition();

    public static int data = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LOCK.lock();
                    while (data == 1) CONDITION.await();
                    data++;
                    System.out.println("生产者生产数据完成=>" + data);
                    Thread.sleep(1000);
                    CONDITION.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }, "producer").start();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    LOCK.lock();
                    while (data == 0) CONDITION.await();
                    data--;
                    System.out.println("消费者消费数据完成=>" + data);
                    Thread.sleep(1000);
                    CONDITION.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }, "consumer").start();
    }
}

class Max {

    public static int result = Integer.MIN_VALUE;

    public static void main(String[] args) {
        max(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        result = Integer.MIN_VALUE;
        max(new int[]{-1, -2, -3});
    }

    public static void max(int[] data) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int datum : data) {
            sum += datum;
            if (sum <= 0) sum = 0;
            max = Math.max(max, datum);
            result = Math.max(result, sum);
        }
        if (max <= 0) System.out.println(max);
        else System.out.println(result);
    }
}