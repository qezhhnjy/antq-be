package com.qezhhnjy.antq.algorithm.study.linked;

/**
 * @author zhaoyangfu
 * @date 2022/3/10-9:03
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示
 */
public class Binary {

    public static void main(String[] args) {
        long a = -1234732119130824132L;
        System.out.println(Long.toBinaryString(a));
        int count = 0;
        while (a != 0) {
            if (a % 2 == 1) count++;
            a = a >>> 1;
        }
        System.out.println(count);
    }
}
