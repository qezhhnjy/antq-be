package com.qezhhnjy.antq.oauth.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.entity.sys.LoginInfo;
import com.qezhhnjy.antq.oauth.utils.AgentUtil;
import com.qezhhnjy.antq.service.sys.LoginInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-19:41
 */
@RestController
@RequestMapping("/login-info")
@Slf4j
public class LoginInfoController {

    @Resource
    private LoginInfoService   loginInfoService;
    @Resource
    private HttpServletRequest request;

    @PostMapping("/query")
    public BaseResult<PageInfo<LoginInfo>> query(@RequestBody Query query) {
        PageInfo<LoginInfo> pageInfo = loginInfoService.query(query);
        return BaseResult.success(pageInfo);
    }

    @GetMapping("ip")
    public BaseResult<LoginInfo> ip() {
        LoginInfo loginInfo = AgentUtil.getUserAgent(request);
        return BaseResult.success(loginInfo);
    }
}
