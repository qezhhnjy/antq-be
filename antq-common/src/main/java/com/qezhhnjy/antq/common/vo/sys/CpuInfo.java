package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyangfu
 * @date 2022/1/15-22:26
 */
@Data
public class CpuInfo implements Serializable {

    private String  name;
    private Integer coreNum;
    private Double  sys;
    private Double  used;
    private Double  wait;
    private Double  free;
    private Double  temperature;
    private Double  voltage;
}
