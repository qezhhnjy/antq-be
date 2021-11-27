package com.qezhhnjy.antq.common.enums;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:35
 */
public enum ResultCode {

    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
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
