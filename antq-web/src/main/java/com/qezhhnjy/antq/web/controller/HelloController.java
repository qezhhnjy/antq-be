package com.qezhhnjy.antq.web.controller;

import com.qezhhnjy.antq.common.BaseResult;
import com.qezhhnjy.antq.web.holder.LoginUserHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-13:43
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Resource
    private LoginUserHolder loginUserHolder;

    @GetMapping("/user")
    public BaseResult<String> user() {
        String currentUser = loginUserHolder.getCurrentUser();
        return BaseResult.success(currentUser);
    }
}
