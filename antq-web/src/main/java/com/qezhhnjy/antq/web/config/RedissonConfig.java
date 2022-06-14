package com.qezhhnjy.antq.web.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoyangfu - 2021/4/10 16:29
 * spring:
 * redis:
 * database: 1
 * host: localhost
 * port: 6379
 * timeout: 3000
 * password: aims2016
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;
    private String port;
    private String password;
    private int    timeout;

    @Bean
    public RedissonClient redisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s", host, port))
                .setPassword(password)
                .setTimeout(timeout)
                .setDatabase(2);

        return Redisson.create(config);
    }
}
