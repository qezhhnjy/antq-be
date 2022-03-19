package com.qezhhnjy.antq.algorithm.study.od;

import lombok.Data;

import java.util.Objects;

/**
 * @author zhaoyangfu
 * @date 2022/3/15-21:03
 * 18、TLV解码
 * TLV编码是按[Tag Length Value]格式进行编码的，一段码流中的信元用Tag标识，Tag在码流中唯一不重复，Length表示信元Value的长度，Value表示信元的值。
 * 码流以某信元的Tag开头，Tag固定占一个字节，Length固定占两个字节，字节序为小端序。
 * 现给定TLV格式编码的码流，以及需要解码的信元Tag，请输出该信元的Value。
 * 输入码流的16机制字符中，不包括小写字母，且要求输出的16进制字符串中也不要包含小写字母；码流字符串的最大长度不超过50000个字节。
 */
public class question18 {

    public static void main(String[] args) {
        String tag = "31";
        String stream = "32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC";
        String[] split = stream.split(" ");
        int index = 0;
        TLV tlv = new TLV();
        while (index < split.length) {
            int len = Integer.parseInt(split[index + 2] + split[index + 1], 16);
            if (Objects.equals(tag, split[index])) {
                tlv.setTag(split[index]);
                tlv.setLen(len);
                tlv.setValue(new String[len]);
                for (int i = 0; i < len; i++) {
                    tlv.getValue()[i] = split[index + 3 + i];
                }
                break;
            }
            index += len + 3;
        }
        System.out.println(tlv);
    }

    @Data
    public static class TLV {
        private String   tag;
        private int      len;
        private String[] value;
    }
}
