package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;

/**
 * @author zhaoyangfu
 * @date 2022/1/1-19:35
 */
@Data
public class LoginVO {

    private String username;
    private String password;
    private String grantType;
    private String clientId;
    private String clientSecret;
}
