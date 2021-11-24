package com.qezhhnjy.sso.server;

import com.qezhhnjy.antq.common.BaseResult;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-16:46
 */
@SpringBootApplication(scanBasePackages = {"com.qezhhnjy.antq","com.qezhhnjy.sso"})
@MapperScan(basePackages = "com.qezhhnjy.antq.mapper")
@RestController
@RequestMapping
@EnableResourceServer
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

    @GetMapping("/hello")
    public BaseResult<String> hello() {
        return BaseResult.success("hello");
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
