package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-14:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
public class User extends BaseEntity {
    private Long id;
    private String avatar;
    private String username;
    private String password;
    private String salt;
    private String nickName;
    private String phone;
    private String email;
    private Byte gender;
    private Byte status;
}
