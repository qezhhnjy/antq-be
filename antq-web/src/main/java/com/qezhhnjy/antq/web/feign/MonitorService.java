package com.qezhhnjy.antq.web.feign;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.vo.sys.MemoryInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhaoyangfu
 * @date 2022/1/16-16:24
 */
@FeignClient(value = "antq-monitor",fallback = OauthServiceFallback.class)
@Service
public interface MonitorService {

    @GetMapping("/monitor")
    BaseResult<MemoryInfo> info();
}
