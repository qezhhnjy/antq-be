package com.qezhhnjy.antq.common.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/4/18-15:29
 */
@Data
public class MessageQuery {

    @NotNull
    private String        from;
    private String        to;
    private Long          groupId;
    private LocalDateTime time;
}
