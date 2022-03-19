package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-22:17
 * 20、工号不够用了怎么办？
 * 3020年，空间通信集团的员工人数突破20亿人，即将遇到现有工号不够用的窘境。
 * 现在，请你负责调研新工号系统。继承历史传统，新的工号系统由小写英文字母（a-z）和数字（0-9）两部分构成。
 * 新工号由一段英文字母开头，之后跟随一段数字，比如"aaahw0001","a12345","abcd1","a00"。注意新工号不能全为字母或者数字,允许数字部分有前导0或者全为0。
 * 但是过长的工号会增加同事们的记忆成本，现在给出新工号至少需要分配的人数X和新工号中字母的长度Y，求新工号中数字的最短长度Z。
 */
public class question20 {

    public static void main(String[] args) {
        int x = Integer.MAX_VALUE / 100;
        int y = 3;
        int z = 1;
        if (x > 260) {
            int a = 1;
            for (int i = 0; i < y; i++) {
                a *= 26;
            }
            while (a < x) {
                a *= 10;
                z++;
            }
        }
        System.out.println(z);
    }
}
