package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhaoyangfu
 * @date 2021/11/27-18:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_role")
@Accessors(chain = true)
public class UserRole extends BaseEntity {
    private Long id;
    private Long userId;
    private Long roleId;
}
