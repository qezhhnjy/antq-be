package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-15:29
 * 为了充分发挥GPU算力，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务，
 * 假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成.
 * GPU一次可执行任务数 n
 * 任务数组长度 len
 * 任务数组 []
 * 方法类似限流的漏桶原理
 */
public class question2 {

    public static void main(String[] args) {
        int n = 3;
        int[] tasks = new int[]{1, 2, 999, 3, 4, 5};
        int bucket = 0;
        int count = 0;
        for (int task : tasks) {
            bucket += task;
            if (bucket >= n) bucket -= n;
            else bucket = 0;
            count++;
        }
        if (bucket != 0) {
            if (bucket % n == 0) count += bucket / n;
            else count += bucket / n + 1;
        }
        System.out.println(count);
    }
}
