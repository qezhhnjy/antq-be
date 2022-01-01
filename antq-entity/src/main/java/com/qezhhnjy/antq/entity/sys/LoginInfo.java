package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-19:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_login_info")
public class LoginInfo extends BaseEntity {

    private Long   id;
    private String username;
    private String browser;
    private String browserVersion;
    private String engine;
    private String engineVersion;
    private String os;
    private String platform;
    private String ip;
    private String client;
    private LocalDateTime loginTime;
    private String region;
}
