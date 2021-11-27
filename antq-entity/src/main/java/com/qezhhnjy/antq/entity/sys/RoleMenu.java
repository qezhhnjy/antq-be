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
@TableName("sys_role_menu")
@Accessors(chain = true)
public class RoleMenu extends BaseEntity {
    private Long id;
    private Long roleId;
    private Long menuId;
}
