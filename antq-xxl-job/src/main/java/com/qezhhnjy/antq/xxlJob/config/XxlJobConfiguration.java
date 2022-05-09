package com.qezhhnjy.antq.xxlJob.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoyangfu
 * @date 2022/5/7-16:00
 */
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobConfiguration {

    @Value("${record.xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${record.xxl.job.executor.appname}")
    private String appName;

    @Value("${record.xxl.job.executor.ip}")
    private String ip;

    @Value("${record.xxl.job.executor.port}")
    private int port;

    @Value("${record.xxl.job.accessToken}")
    private String accessToken;

    @Value("${record.xxl.job.executor.logpath}")
    private String logPath;

    @Value("${record.xxl.job.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor executor = new XxlJobSpringExecutor();
        executor.setAdminAddresses(adminAddresses);
        executor.setAppname(appName);
        executor.setIp(ip);
        executor.setPort(port);
        executor.setAccessToken(accessToken);
        executor.setLogPath(logPath);
        executor.setLogRetentionDays(logRetentionDays);

        return executor;
    }
}
