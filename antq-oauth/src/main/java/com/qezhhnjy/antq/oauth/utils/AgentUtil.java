package com.qezhhnjy.antq.oauth.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.qezhhnjy.antq.entity.sys.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.*;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-18:59
 */
@Slf4j
public class AgentUtil {

    private static final String DBNAME = "ip2region.db";

    private static DbSearcher searcher;

    static {
        try {
            searcher = new DbSearcher(new DbConfig(), ResourceUtil.readBytes(DBNAME));
        } catch (DbMakerConfigException e) {
            e.printStackTrace();
        }
    }

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
        loginInfo.setIp(getIpAddress(request));
        loginInfo.setLoginTime(LocalDateTime.now());
        loginInfo.setRegion(getCityInfo(loginInfo.getIp()));
        return loginInfo;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("ip=>{}", ip);
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }


    public static String getCityInfo(String ip) {
        if (!Util.isIpAddress(ip)) {
            log.error("Invalid ip address=>{}", ip);
        }
        //B-tree, B树搜索（更快）
        //Binary,使用二分搜索(最慢)
        //Memory,加载内存（最快）
        try {
            DataBlock dataBlock = searcher.memorySearch(ip);
            String ipInfo = dataBlock.getRegion();
            // 中国|0|云南省|玉溪市|移动 这种结果替换掉0
            if (StrUtil.isNotBlank(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
            }
            return ipInfo;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

}
