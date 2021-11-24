package com.qezhhnjy.sso.client;

import com.qezhhnjy.antq.common.BaseResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author zhaoyangfu
 * @date 2021/11/24-8:28
 */
@SpringBootApplication(scanBasePackages = {"com.qezhhnjy.antq", "com.qezhhnjy.sso"})
@MapperScan(basePackages = "com.qezhhnjy.antq.mapper")
@RestController
@RequestMapping
public class SsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class, args);
    }

    @GetMapping("/hello")
    public BaseResult<String> hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return BaseResult.success(authentication.getName() + Arrays.toString(authentication.getAuthorities().toArray()));
    }
}
