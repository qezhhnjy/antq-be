package com.qezhhnjy.antq.oauth.controller;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyangfu
 * @date 2022/1/14-11:25
 */
@RequestMapping("/monitor")
@RestController
@Slf4j
public class MonitorInfoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping
    public BaseResult<MemoryInfo> monitor() {
        log.info("monitor info...");
        MemoryInfo info = MemoryInfo.info(applicationName);
        return BaseResult.success(info);
    }
}
