package com.qezhhnjy.antq.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/1/7-0:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_record")
@Accessors(chain = true)
public class TimelineRecord extends BaseEntity {
    private Long          id;
    private String        title;
    private String        content;
    private String        username;
    private String        avatar;
    private LocalDateTime recordTime;
}
