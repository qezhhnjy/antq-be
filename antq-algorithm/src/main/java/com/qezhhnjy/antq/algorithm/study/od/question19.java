package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-21:29
 * 19、用连续自然数之和来表达整数
 * 一个整数可以由连续的自然数之和来表示。给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式。
 */
public class question19 {

    public static void main(String[] args) {
        int n = 9;
        int m = 1;
        // 反向思维：m个连续数字之和为n时，假设起始点为k，满足 n = (k + k + m - 1)/2 * m;
        // 所以 n/m = (2k + m - 1)/2=k + (m - 1)/2;
        // k = n/m - (m - 1)/2;
        // 同时考虑m可能的最大值：即 k = 1时，n = (1 + m)/2 * m => n = (m + m*m)/2 => 2n = m*(m+1) => m*m < 2n => m < Math.sqrt(2n)
        while (m < Math.sqrt(2 * n)) {
            double k = (double) n / m - (double) (m - 1) / 2;
            double ceil = Math.ceil(k);
            if (k == ceil) {
                int begin = (int) ceil;
                print(begin, m);
            }
            m++;
        }
    }

    public static void print(int begin, int m) {
        StringBuilder sb = new StringBuilder();
        sb.append(begin);
        for (int i = 1; i < m; i++) {
            sb.append("+").append(begin + i);
        }
        System.out.println("9=" + sb);
    }
}

