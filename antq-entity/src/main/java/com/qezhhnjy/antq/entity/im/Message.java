package com.qezhhnjy.antq.entity.im;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qezhhnjy.antq.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-11:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("im_message")
public class Message extends BaseEntity {

    private Long          id;
    private String        send;
    private String        receive;
    private Integer       cmd;
    @TableField("message_type")
    private Integer       type;
    private Integer       chatType;
    private Long          groupId;
    private String        content;
    private LocalDateTime sendTime;
}
