package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2021/12/21-15:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_notice")
public class Notice extends BaseEntity {

    private long          id;
    private long          userId;
    private String        avatar;
    private String        title;
    private String        message;
    private String        extra;
    private Byte          Status;
    private Byte          type;
    private LocalDateTime productTime;
}
