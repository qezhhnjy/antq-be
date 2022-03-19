package com.qezhhnjy.antq.jimu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaoyangfu
 * @date 2022/2/25-21:45
 */
@SpringBootApplication(scanBasePackages = {"org.jeecg.modules.jmreport", "com.qezhhnjy.antq.jimu"})
@Slf4j
public class AntqJimuApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntqJimuApplication.class, args);
    }
}
