package com.qezhhnjy.antq.im.socketio;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @date 2022/4/17-19:57
 */
@Data
public class Message {

    private String username;
    private LocalDateTime time;
    private String content;
}
