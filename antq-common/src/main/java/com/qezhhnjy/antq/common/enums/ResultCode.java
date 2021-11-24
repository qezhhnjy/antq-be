package com.qezhhnjy.antq.common.enums;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:35
 */
public enum ResultCode {

    SUCCESS(1000, "响应成功"),
    FAILED(1100, "响应失败"),

    FTP_ERROR(2000, "FTP错误"),

    DATA_NOT_EXIST(3000, "数据不存在"),

    UNKNOWN(-1, "未知响应");

    public final int    code;
    public final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
