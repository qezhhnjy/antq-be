package com.qezhhnjy.antq.common.util;

/**
 * @author zhaoyangfu
 * @date 2022/1/14-16:41
 */
public class CapacityUtil {

    public static int gb(long b) {
        return Math.toIntExact(b / (1024 * 1024 * 1024));
    }

    public static int mb(long b) {
        return Math.toIntExact(b / (1024 * 1024));
    }

    public static int kb(long b) {
        return Math.toIntExact(b / 1024);
    }
}
