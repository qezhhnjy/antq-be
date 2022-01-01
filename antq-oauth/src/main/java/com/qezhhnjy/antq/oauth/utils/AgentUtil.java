package com.qezhhnjy.antq.oauth.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.qezhhnjy.antq.entity.sys.LoginInfo;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-18:59
 */
public class AgentUtil {

    public static LoginInfo getUserAgent(HttpServletRequest request) {
        UserAgent agent = UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setBrowser(agent.getBrowser().getName());
        loginInfo.setBrowserVersion(agent.getVersion());
        loginInfo.setEngine(agent.getEngine().getName());
        loginInfo.setEngineVersion(agent.getEngineVersion());
        loginInfo.setOs(agent.getOs().getName());
        loginInfo.setPlatform(agent.getPlatform().getName());
        loginInfo.setClient(agent.isMobile() ? "手机端" : "电脑端");
        loginInfo.setIp(getIpAddr(request));
        loginInfo.setLoginTime(LocalDateTime.now());
        return loginInfo;
    }

    //其中ip的获取方式
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            //LOGGER.error("X-Real-IP:"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }
}
