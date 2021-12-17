package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.UserVO;
import com.qezhhnjy.antq.oauth.holder.LoginUserHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-13:43
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/user")
    public BaseResult<UserVO> user() {
        return BaseResult.success(loginUserHolder.getCurrentUser());
    }

    @GetMapping("/hello")
    public BaseResult<String> hello() {
        return BaseResult.success("hello");
    }
}
