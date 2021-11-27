package com.qezhhnjy.antq.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-13:29
 */
@SpringBootApplication(scanBasePackages = "com.qezhhnjy.antq")
@MapperScan(basePackages = "com.qezhhnjy.antq.mapper")
@EnableDiscoveryClient
public class AntqWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqWebApplication.class, args);
    }
}
