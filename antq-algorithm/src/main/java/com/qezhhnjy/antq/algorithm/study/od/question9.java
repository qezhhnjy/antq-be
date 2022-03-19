package com.qezhhnjy.antq.algorithm.study.od;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-22:16
 * 给定一个非空数组（列表），其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，十进制最低位相同的元素，相对位置保持不变。
 * 当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制值最低位
 */
public class question9 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 5, -21, 22, 11, 55, -101, 43, 8, 7, 32};
        Map<Integer, Info> map = new LinkedHashMap<>();
        for (int datum : Arrays.stream(data).distinct().toArray()) {
            // -21 % 10 == -1
            map.put(datum, new Info(datum, Math.abs(datum % 10)));
        }
        System.out.println(Arrays.toString(map.values().stream().sorted(Comparator.comparing(Info::getCount)).map(Info::getValue).toArray()));
    }
}

