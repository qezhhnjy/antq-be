package com.qezhhnjy.antq.nacos;

import com.qezhhnjy.antq.common.consts.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoyangfu
 * @date 2022/1/16-19:33
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RequestMapping("/nacos")
@RestController
@Slf4j
@RefreshScope
public class NacosApplication {

    @Value("${antq}")
    private String a;

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }

    @GetMapping("/a")
    public BaseResult<String> a() {
        return BaseResult.success(a);
    }
}
