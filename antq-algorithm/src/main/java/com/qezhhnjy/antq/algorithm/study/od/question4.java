package com.qezhhnjy.antq.algorithm.study.od;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-20:12
 * 4、数组去重和排序
 * 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序.
 */
public class question4 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 3, 3, 2, 4, 4, 4, 5};
        Map<Integer, Info> map = new LinkedHashMap<>();
        for (int datum : data) {
            map.computeIfPresent(datum, (k, v) -> {
                v.setCount(v.getCount() + 1);
                return v;
            });
            map.putIfAbsent(datum, new Info(datum, 1));
        }
        System.out.println(Arrays.toString(map.values().stream().sorted(Comparator.comparing(Info::getCount).reversed()).map(Info::getValue).toArray()));
    }
}

