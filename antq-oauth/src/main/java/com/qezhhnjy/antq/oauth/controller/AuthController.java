package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.sys.LoginInfo;
import com.qezhhnjy.antq.oauth.utils.AgentUtil;
import com.qezhhnjy.antq.service.sys.LoginInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 * Created by macro on 2020/7/17.
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class AuthController {

    @Resource
    private TokenEndpoint      tokenEndpoint;
    @Resource
    private LoginInfoService   loginInfoService;
    @Resource
    private HttpServletRequest request;

    /**
     * Oauth2登录认证
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public BaseResult<OAuth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        LoginInfo loginInfo = AgentUtil.getUserAgent(request);
        loginInfo.setUsername(parameters.get("username"));
        log.info("loginInfo=>{}", loginInfo);
        loginInfoService.save(loginInfo);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return BaseResult.success(oAuth2AccessToken);
    }
}
