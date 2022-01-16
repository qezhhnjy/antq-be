package com.qezhhnjy.antq.web.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import com.qezhhnjy.antq.web.feign.GatewayService;
import com.qezhhnjy.antq.web.feign.MonitorService;
import com.qezhhnjy.antq.web.feign.OauthService;
import com.qezhhnjy.antq.web.vo.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
}
