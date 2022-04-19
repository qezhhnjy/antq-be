package com.qezhhnjy.antq.common.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-15:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageQuery extends Query {

    private String        send;
    private String        receive;
    private Long          groupId;
    private LocalDateTime time;
}
