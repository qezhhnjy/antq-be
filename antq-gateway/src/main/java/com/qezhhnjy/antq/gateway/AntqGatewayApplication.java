package com.qezhhnjy.antq.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class AntqGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqGatewayApplication.class, args);
        log.info("网关模块启动成功");
    }

}
