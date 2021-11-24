package com.qezhhnjy.antq.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-14:58
 */
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @TableField(value = "company_id", fill = FieldFill.INSERT)
    private String companyId;

    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "updater", fill = FieldFill.UPDATE)
    private String updater;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "delete_flag")
    @TableLogic
    private Boolean deleteFlag;

}
