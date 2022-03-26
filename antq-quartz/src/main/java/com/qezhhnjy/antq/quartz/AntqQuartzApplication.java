package com.qezhhnjy.antq.quartz;

import cn.hutool.extra.spring.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

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
@Import(SpringUtil.class)
public class AntqQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqQuartzApplication.class, args);
    }
}
