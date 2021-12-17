package com.qezhhnjy.antq.common.exception;

import com.qezhhnjy.antq.common.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException extends RuntimeException {

    private ResultCode code;

    public BizException(ResultCode code) {
        super(code.msg);
        this.code = code;
    }

    public BizException(ResultCode code, String msg) {
        super(msg);
        this.code = code;
    }
}
