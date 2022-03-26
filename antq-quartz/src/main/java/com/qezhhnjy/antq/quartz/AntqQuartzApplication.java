package com.qezhhnjy.antq.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-23 20:33
 */
@SpringBootApplication
@MapperScan(basePackages = "com.qezhhnjy.antq.quartz.mapper")
public class AntqQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqQuartzApplication.class, args);
    }
}
