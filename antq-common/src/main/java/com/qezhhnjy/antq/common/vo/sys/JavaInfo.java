package com.qezhhnjy.antq.common.vo.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyangfu
 * @date 2022/1/15-22:20
 */
@Data
public class JavaInfo implements Serializable {

    private String name;
    private String version;
    private String vendor;
    private String info;
}
