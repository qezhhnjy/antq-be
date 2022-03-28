package com.qezhhnjy.antq.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:22
 */
@SpringBootApplication(scanBasePackages = "com.qezhhnjy.antq")
@MapperScan(basePackages = "com.qezhhnjy.antq.mapper")
@EnableDiscoveryClient
@EnableCaching
public class AntqOauthApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AntqOauthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
