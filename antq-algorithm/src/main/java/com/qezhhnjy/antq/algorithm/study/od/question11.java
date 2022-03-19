package com.qezhhnjy.antq.algorithm.study.od;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-19:33
 * 11、数组组成的最小数字
 * 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出（如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
 */
public class question11 {

    public static void main(String[] args) {
        int[] data = new int[]{21, 30, 62, 5, 31};
        List<String> list = Arrays.stream(data).sorted().limit(3).mapToObj(String::valueOf).sorted().collect(Collectors.toList());
        System.out.println(String.join("", list));
    }
}
