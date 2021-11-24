package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-15:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class Menu extends BaseEntity {
    private Long id;
    private String menuName;
    private String permission;
    private String path;
    private Long   parentId;
    private String icon;
    private Byte   sort;
    private Byte   type;
    private Byte   status;
}
