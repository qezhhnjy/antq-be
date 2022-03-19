package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-19:53
 * 3、按索引范围翻转文章片段
 * 输入一个英文文章片段，翻转指定区间的单词顺序，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a developer. "，区间[0,3]，则输出"developer. a am I"。
 */
public class question3 {

    public static void main(String[] args) {
        String section = "I am a developer.";
        String[] words = section.split(" ");
        int begin = 0;
        int end = 3;
        while (begin <= end) {
            String a = words[begin];
            String b = words[end];
            words[begin] = b;
            words[end] = a;
            begin++;
            end--;
        }
        section = String.join(" ", words);
        System.out.println(section);
    }
}
