package com.qezhhnjy.antq.web.controller;

import cn.hutool.core.collection.CollUtil;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import com.qezhhnjy.antq.entity.RedisInfo;
import com.qezhhnjy.antq.web.feign.GatewayService;
import com.qezhhnjy.antq.web.feign.MonitorService;
import com.qezhhnjy.antq.web.feign.OauthService;
import com.qezhhnjy.antq.web.util.RedisUtil;
import com.qezhhnjy.antq.web.vo.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private static final String SYSTEM_INFO = "SYSTEM_INFO";

    @Value("${spring.application.name}")
    private String         applicationName;
    @Resource
    private MonitorService monitorService;
    @Resource
    private OauthService   oauthService;
    @Resource
    private GatewayService gatewayService;
    @Resource
    private RedisUtil      redisUtil;
    @Resource
    private RedissonClient redisson;

    @Scheduled(fixedRate = 10_000)
    public void systemInfo() {
        log.info("monitor info...");
        SystemInfo info = SystemInfo.info();
        List<MemoryInfo> jvmList = info.getJvmList();
        jvmList.add(MemoryInfo.info(applicationName));
        jvmList.add(oauthService.info().getData());
        jvmList.add(monitorService.info().getData());
        jvmList.add(gatewayService.info().getData());
        redisson.getBucket(SYSTEM_INFO).set(info);
    }

    @GetMapping
    public BaseResult<SystemInfo> monitor() {
        RBucket<SystemInfo> bucket = redisson.getBucket(SYSTEM_INFO);
        return BaseResult.success(bucket.get());
    }

    @GetMapping("/redis")
    public BaseResult<List<RedisInfo>> redis() {
        RedisConnectionFactory factory = redisUtil.conn();
        if (factory != null) {
            Properties info = factory.getConnection().info();
            if (CollUtil.isNotEmpty(info)) {
                List<RedisInfo> result = new ArrayList<>();
                info.forEach((prop, value) -> result.add(RedisInfo.populate(prop, value)));
                result.sort((o1, o2) -> o1.getProp().compareToIgnoreCase(o2.getProp()));
                return BaseResult.success(result);
            }
        }
        return BaseResult.error("Redis连接异常");
    }
}
