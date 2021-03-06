package com.qezhhnjy.antq.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2021/11/23-14:58
 */
@Data
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    @TableField(value = "company_id", fill = FieldFill.INSERT)
    @JsonIgnore
    private String companyId;

    @TableField(value = "creator", fill = FieldFill.INSERT)
    @JsonIgnore
    private String creator;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    private LocalDateTime createTime;

    @TableField(value = "updater", fill = FieldFill.UPDATE)
    @JsonIgnore
    private String updater;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonIgnore
    private LocalDateTime updateTime;

    @TableField(value = "delete_flag")
    @TableLogic
    @JsonIgnore
    private Boolean deleteFlag;

}
