package com.qezhhnjy.antq.algorithm.study.od;

import java.util.Arrays;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-22:08
 * 一个工厂有m条流水线，来并行完成n个独立的作业，该工厂设置了一个调度系统，在安排作业时，总是优先执行处理时间最短的作业。
 * 现给定流水线个数m，需要完成的作业数n, 每个作业的处理时间分别为t1,t2…tn。请你编程计算处理完所有作业的耗时为多少？
 * 当n>m时，首先处理时间短的m个作业进入流水线，其他的等待，当某个作业完成时，依次从剩余作业中取处理时间最短的进入处理
 */
public class question8 {

    public static void main(String[] args) {
        int m = 3, n = 5;
        int[] t = new int[]{8, 4, 3, 2, 10};
        t = Arrays.stream(t).sorted().toArray();

    }
}
