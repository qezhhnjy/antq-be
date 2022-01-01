package com.qezhhnjy.antq.web.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

    /**
     * 主要实现web监控的配置处理
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        // 表示进行druid监控的配置处理操作
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 白名单
        // servletRegistrationBean.addInitParameter("allow", "localhost");
        // 黑名单
        // servletRegistrationBean.addInitParameter("deny", "129.168.1.12");
        // 用户名
        servletRegistrationBean.addInitParameter("loginUsername", "aims");
        // 密码
        servletRegistrationBean.addInitParameter("loginPassword", "aims2016");
        // 是否可以重置数据源
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");//所有请求进行监控处理
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");//排除
        return filterRegistrationBean;
    }

}