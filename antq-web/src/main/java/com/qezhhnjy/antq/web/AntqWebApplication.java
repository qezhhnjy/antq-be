package com.qezhhnjy.antq.web;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-13:29
 */
@SpringBootApplication(scanBasePackages = "com.qezhhnjy.antq")
@MapperScan(basePackages = {"com.qezhhnjy.antq.mapper", "com.qezhhnjy.antq.quartz.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
@Import(SpringUtil.class)
@Slf4j
@EnableScheduling
public class AntqWebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AntqWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
