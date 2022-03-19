package com.qezhhnjy.antq.algorithm.study.od;

import java.util.Arrays;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-14:54
 * 1、字符串筛选排序
 * 输入一个由n个大小写字母组成的字符串，按照Ascii码值从小到大的排序规则，查找字符串中第k个最小ascii码值的字母（k>=1），输出该字母所在字符串的位置索引(字符串的第一个字符位置索引为0）。
 * k如果大于字符串长度，则输出最大ascii值的字母所在字符串的位置索引，如果有重复的字母，则输出字母的最小位置索引
 */
public class question1 {

    public static void main(String[] args) {
        String str = "AbCdeFgz";
        int k = 5;
        int max = -1;
        int[] mark = new int['z' - 'A' + 1];
        Arrays.fill(mark, -1);
        System.out.println(mark.length);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'A';
            if (mark[index] == -1) mark[index] = i;
        }
        System.out.println(Arrays.toString(mark));
        for (int i : mark) {
            if (i != -1) {
                k--;
                if (k == 0) {
                    System.out.println(i);
                    return;
                }
                max = i;
            }
        }
        System.out.println(max);
    }
}
