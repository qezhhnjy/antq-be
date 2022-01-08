package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/1/8-18:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_blog")
public class Blog extends BaseEntity {

    private Long          id;
    private String        username;
    private String        avatar;
    private String        title;
    private String        summary;
    private String        icon;
    private String        tags;
    private String        raw;
    private String        html;
    private LocalDateTime editTime;
}
