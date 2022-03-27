package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhaoyangfu
 * @date 2022/3/27-16:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_quartz_log")
public class QuartzLog extends BaseEntity {

    private Long   id;
    private String jobGroup;
    private String jobName;
    private Long   millisecond;
    private String exception;
}
