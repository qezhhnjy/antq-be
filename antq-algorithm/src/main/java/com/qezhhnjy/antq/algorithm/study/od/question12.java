package com.qezhhnjy.antq.algorithm.study.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-19:48
 * VLAN是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入VLAN ID(1-4094之间的整数)的概念。
 * 定义一个VLAN ID的资源池(下称VLAN资源池)，资源池中连续的VLAN用开始VLAN-结束VLAN表示，
 * 不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。现在有一个VLAN资源池，
 * 业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池
 */
public class question12 {

    public static void main(String[] args) {
        // 需要从pool中取出2, 并返回取出vlan后的资源池
        String pool = "1-5,7,9";
        String vlan = "2";
        int iVlan = Integer.parseInt(vlan);

        List<String> list = Arrays.stream(pool.split(",")).collect(Collectors.toList());
        if (list.contains(vlan)) {
            list.remove(vlan);
            System.out.println(String.join(",", list));
            return;
        }
        list.stream().filter(s -> {
            if (s.contains("-")) {
                String[] split = s.split("-");
                return Integer.parseInt(split[0]) <= iVlan && Integer.parseInt(split[1]) >= iVlan;
            }
            return false;
        }).findFirst().ifPresent(s -> {
            list.remove(s);
            String[] split = s.split("-");
            int begin = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            List<String> data = new ArrayList<>();

            if (iVlan == begin) data.add((begin + 1) + "-" + end);
            else if (iVlan == end) data.add(begin + "-" + (end - 1));
            else {
                int l2 = iVlan - 1, r1 = iVlan + 1;
                if (begin == l2) data.add(String.valueOf(begin));
                else data.add(begin + "-" + l2);
                if (r1 == end) data.add(String.valueOf(r1));
                else data.add(r1 + "-" + end);
            }
            list.addAll(data);
        });
        System.out.println(String.join(",", list));
    }
}
