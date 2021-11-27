package com.qezhhnjy.antq.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:22
 */
@SpringBootApplication(scanBasePackages = "com.qezhhnjy.antq")
@MapperScan(basePackages = "com.qezhhnjy.antq.mapper")
@EnableDiscoveryClient
public class AntqOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqOauthApplication.class, args);
    }
}
