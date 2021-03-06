package com.qezhhnjy.antq.web.holder;

import com.qezhhnjy.antq.common.consts.AuthConstant;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 获取登录用户信息
 * Created by macro on 2020/6/17.
 */
@Component
@Slf4j
public class LoginUserHolder {

    @Resource
    private UserService userService;

    public UserVO getCurrentUser() {
        //从Header中获取用户信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Long userId = Long.valueOf(request.getHeader(AuthConstant.HEADER_USER_ID));
        return userService.detail(userId);
    }
}
