package com.qezhhnjy.antq.algorithm.study.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-21:46
 * 如果3个正整数(a,b,c)满足a2 + b2 = c2的关系，则称(a,b,c)为勾股数（著名的勾三股四弦五），
 * 为了探索勾股数的规律，我们定义如果勾股数(a,b,c)之间两两互质（即a与b，a与c，b与c之间均互质，没有公约数），
 * 则其为勾股数元祖（例如(3,4,5)是勾股数元祖，(6,8,10)则不是勾股数元祖）。请求出给定范围[N,M]内，所有的勾股数元祖
 */
public class question7 {

    public static void main(String[] args) {
        int left = 1;
        int right = 10000;

        List<Integer[]> list = new ArrayList<>();
        int count = 0;

        for (int a = left; a <= right - 2; a++) {
            for (int b = a + 1; b <= right - 1; b++) {
                double c = Math.sqrt(a * a + b * b);
                count++;
                double ceil = Math.ceil(c);
                if (c != ceil) continue;
                int cInt = (int) ceil;
                if (c <= right && cInt == c && !existPrime(a, b) && !existPrime(a, cInt) && !existPrime(b, cInt)) {
                    list.add(new Integer[]{a, b, cInt});
                }
            }
        }
        list.forEach(d -> System.out.println(Arrays.toString(d)));
        System.out.println(count);
    }

    public static boolean existPrime(int a, int b) {
        int min = Math.min(a, b);
        for (int i = 2; i < min / 2; i++) {
            if (a % i == 0 && b % i == 0) return true;
        }
        return false;
    }
}
