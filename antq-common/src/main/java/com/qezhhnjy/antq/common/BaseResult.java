package com.qezhhnjy.antq.common;

import com.qezhhnjy.antq.common.enums.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:34
 */
@Data
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    private static final ResultCode SUCCESS = ResultCode.SUCCESS;
    private static final ResultCode FAILED  = ResultCode.FAILED;

    private int    code;
    private String msg;
    private T      data;
    private int    count;

    private BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult<T> count(int count) {
        this.count = count;
        return this;
    }

    public static <T> BaseResult<T> success() {
        return new BaseResult<>(SUCCESS.code, SUCCESS.msg, null);
    }

    public static <T> BaseResult<T> success(T data) {
        return new BaseResult<>(SUCCESS.code, SUCCESS.msg, data);
    }

    public static <T> BaseResult<T> error(int code, String msg) {
        return new BaseResult<>(code, msg, null);
    }

    public static <T> BaseResult<T> error(String msg) {
        return new BaseResult<>(FAILED.code, msg, null);
    }
}