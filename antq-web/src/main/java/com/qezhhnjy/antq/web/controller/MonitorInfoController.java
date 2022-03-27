package com.qezhhnjy.antq.web.controller;

import cn.hutool.json.JSONUtil;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import com.qezhhnjy.antq.web.feign.GatewayService;
import com.qezhhnjy.antq.web.feign.MonitorService;
import com.qezhhnjy.antq.web.feign.OauthService;
import com.qezhhnjy.antq.web.vo.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * @author zhaoyangfu
 * @date 2022/1/14-11:25
 */
@RequestMapping("/monitor")
@RestController
@Slf4j
public class MonitorInfoController {

    @Value("${spring.application.name}")
    private String         applicationName;
    @Resource
    private MonitorService monitorService;
    @Resource
    private OauthService   oauthService;
    @Resource
    private GatewayService gatewayService;

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping
    public BaseResult<SystemInfo> monitor() {
        log.info("monitor info...");
        SystemInfo info = SystemInfo.info();
        List<MemoryInfo> jvmList = info.getJvmList();
        jvmList.add(MemoryInfo.info(applicationName));
        jvmList.add(oauthService.info().getData());
        jvmList.add(monitorService.info().getData());
        jvmList.add(gatewayService.info().getData());
        return BaseResult.success(info);
    }

    @GetMapping("/redis")
    public BaseResult<?> redis() {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        if (factory != null) {
            Properties info = factory.getConnection().info();
            return BaseResult.success(info);
        }
        return BaseResult.error("Redis连接异常");
    }

    public static void main(String[] args) {
        SystemInfo info = SystemInfo.info();
        System.out.println(JSONUtil.toJsonPrettyStr(info));
    }
}
