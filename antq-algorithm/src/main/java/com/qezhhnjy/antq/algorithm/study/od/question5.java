package com.qezhhnjy.antq.algorithm.study.od;

/**
 * @author zhaoyangfu
 * @date 2022/3/14-20:53
 * 5、字符串序列判定
 * 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。判定S是否是L的有效字串。
 * <p>
 * 判定规则：S中的每个字符在L中都能找到（可以不连续），且S在Ｌ中字符的前后顺序与S中顺序要保持一致。
 * （例如，S="ace"是L="abcde"的一个子序列且有效字符是a、c、e，而"aec"不是有效子序列，且有效字符只有a、e）
 */
public class question5 {

    public static void main(String[] args) {
        String son = "acec";
        String parent = "abcde";
        System.out.println(son(son, parent));
    }


    public static boolean son(String son, String parent) {
        char[] sonChars = son.toCharArray();
        char[] parentChars = parent.toCharArray();

        int index = 0;
        for (char c : sonChars) {
            for (; index < parentChars.length; index++) {
                if (c == parentChars[index]) break;
            }
        }
        return index < parentChars.length;
    }
}
