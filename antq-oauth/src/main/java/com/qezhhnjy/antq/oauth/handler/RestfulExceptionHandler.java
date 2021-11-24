package com.qezhhnjy.antq.oauth.handler;

import cn.hutool.core.util.StrUtil;
import com.qezhhnjy.antq.common.BaseResult;
import com.qezhhnjy.antq.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhaoyangfu
 */
@RestControllerAdvice
@Slf4j
public class RestfulExceptionHandler {

    /**
     * 业务异常处理：
     * 1. 异常中没有自定义信息则返回响应码的通用信息
     * 2. 异常中有自定义信息则返回该信息
     */
    @ExceptionHandler(BizException.class)
    public <T> BaseResult<T> bizExceptionHandler(BizException e) {
        String msg = e.getMessage();
        log.error(msg, e);
        if (StrUtil.isBlank(msg)) return BaseResult.error(e.getCode().code, e.getCode().msg);
        else return BaseResult.error(e.getCode().code, msg);
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(Exception.class)
    public <T> BaseResult<T> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return BaseResult.error(e.getMessage());
    }
}
