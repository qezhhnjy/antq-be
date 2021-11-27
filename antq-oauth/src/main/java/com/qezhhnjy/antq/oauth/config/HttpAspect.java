package com.qezhhnjy.antq.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @author qezhhnjy
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class HttpAspect {

    @Pointcut("execution(public * com.qezhhnjy.antq.oauth.controller..*Controller.*(..))")
    public void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long time = new Date().getTime();
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = Objects.requireNonNull(attrs).getRequest();
        log.info("{} url=>{}", time, req.getRequestURL());
        log.info("{} args=>{}", time, point.getArgs());
        // log.debug("method: {}", req.getMethod());
        // log.debug("ip:port: {}:{}", req.getRemoteHost(), req.getRemotePort());
        // log.debug("method: {}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        Object result = point.proceed();
        log.debug("{} response=>{}", time, result);
        return result;
    }

    @AfterThrowing(throwing = "e", pointcut = "cut()")
    public void afterThrowing(Throwable e) {
        log.error(e.getMessage());
    }
}
