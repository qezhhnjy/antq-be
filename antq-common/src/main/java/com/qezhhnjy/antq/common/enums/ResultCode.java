package com.qezhhnjy.antq.common.enums;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:35
 */
public enum ResultCode {

    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    SUCCESS(1000, "响应成功"),
    USERNAME_PASSWORD_ERROR(1001, "用户名或密码错误"),
    CREDENTIALS_EXPIRED(1002, "该账户的登录凭证已过期，请重新登录!"),
    ACCOUNT_DISABLED(1003, "该账户已被禁用，请联系管理员!"),
    ACCOUNT_LOCKED(1004, "该账号已被锁定，请联系管理员!"),
    ACCOUNT_EXPIRED(1005, "该账号已过期，请联系管理员!"),
    PERMISSION_DENIED(1006, "没有访问权限，请联系管理员!"),

    DATA_NOT_EXIST(2000, "data not exist"),
    FAILED(9999, "响应失败");

    public final int    code;
    public final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
