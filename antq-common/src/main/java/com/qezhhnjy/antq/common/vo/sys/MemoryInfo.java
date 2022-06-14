package com.qezhhnjy.antq.common.vo.sys;

import cn.hutool.system.SystemUtil;
import com.qezhhnjy.antq.common.util.CapacityUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaoyangfu
 * @date 2022/1/15-22:23
 */
@Data
public class MemoryInfo implements Serializable {

    private String  name;
    private Integer total;
    private Integer max;
    private Integer free;
    private Integer used;

    public static MemoryInfo info(String applicationName) {
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.setName(applicationName);
        memoryInfo.setTotal(CapacityUtil.mb(SystemUtil.getTotalMemory()));
        memoryInfo.setMax(CapacityUtil.mb(SystemUtil.getMaxMemory()));
        memoryInfo.setFree(CapacityUtil.mb(SystemUtil.getFreeMemory()));
        memoryInfo.setUsed(memoryInfo.getTotal()- memoryInfo.getFree());
        return memoryInfo;
    }
}
