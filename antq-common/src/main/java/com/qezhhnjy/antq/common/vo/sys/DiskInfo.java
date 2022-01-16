package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;

/**
 * @author zhaoyangfu
 * @date 2022/1/14-20:55
 */
@Data
public class DiskInfo {

    private String  name;
    private Integer total;
    private Integer free;
    private Integer used;
    private Integer read         = 0;
    private Integer write        = 0;
    private Long    transferTime = 0L;
}
