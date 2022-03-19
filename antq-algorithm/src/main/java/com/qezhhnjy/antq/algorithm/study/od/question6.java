package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-21:25
 * 给航天器一侧加装长方形或正方形的太阳能板（图中的红色斜线区域），需要先安装两个支柱（图中的黑色竖条），再在支柱的中间部分固定太阳能板。
 * 但航天器不同位置的支柱长度不同，太阳能板的安装面积受限于最短一侧的那根支柱长度。
 * 支柱间隔1m求最大面积
 */
public class question6 {

    public static void main(String[] args) {
        int[] a = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int min = Integer.MAX_VALUE;
        int area = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
            if (i * min > area) area = i * min;
        }
        System.out.println(area);
    }
}
