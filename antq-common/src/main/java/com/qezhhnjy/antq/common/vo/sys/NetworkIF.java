package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;

/**
 * @author zhaoyangfu
 * @date 2022/1/15-22:30
 */
@Data
public class NetworkIF {

    private String   name;
    private String   displayName;
    // KB
    private Integer  recv;
    private Integer  sent;
    private Long     speed;
    private Long     packetRecv;
    private Long     packetSent;
    private String   alias;
    private String   mac;
    private String[] ipv4;
}
