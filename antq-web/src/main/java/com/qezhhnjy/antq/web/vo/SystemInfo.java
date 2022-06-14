package com.qezhhnjy.antq.web.vo;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.OshiUtil;
import com.qezhhnjy.antq.common.util.CapacityUtil;
import com.qezhhnjy.antq.common.vo.sys.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import oshi.hardware.GlobalMemory;
import oshi.hardware.Sensors;
import oshi.hardware.VirtualMemory;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/15-22:21
 */
@Data
@Slf4j
public class SystemInfo implements Serializable {

    private String           system;
    private String           username;
    private LocalTime        now;
    private JavaInfo         java;
    private List<MemoryInfo> jvmList       = new ArrayList<>();
    private CpuInfo          cpu;
    private MemoryInfo       memory;
    private MemoryInfo       virtual;
    private DiskInfo         disk;
    private List<DiskInfo>   partitionList = new ArrayList<>();
    private List<NetworkIF>  networkIFList = new ArrayList<>();
    private Integer          recv          = 0;
    private Integer          sent          = 0;

    public static SystemInfo info() {
        SystemInfo systemInfo = new SystemInfo();

        systemInfo.setSystem(OshiUtil.getOs().toString());
        systemInfo.setUsername(SystemUtil.getUserInfo().getName());

        JavaInfo java = new JavaInfo();
        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        java.setName(jvmInfo.getName());
        java.setVersion(SystemUtil.getJavaInfo().getVersion());
        java.setVendor(jvmInfo.getVendor());
        java.setInfo(jvmInfo.getInfo());
        systemInfo.setJava(java);

        CpuInfo cpuInfo = new CpuInfo();
        systemInfo.setCpu(cpuInfo);
        cpuInfo.setName(OshiUtil.getProcessor().getProcessorIdentifier().getName());

        cn.hutool.system.oshi.CpuInfo oshiCpuInfo = OshiUtil.getCpuInfo();
        cpuInfo.setCoreNum(oshiCpuInfo.getCpuNum());
        cpuInfo.setSys(oshiCpuInfo.getSys());
        cpuInfo.setUsed(oshiCpuInfo.getUsed());
        cpuInfo.setWait(oshiCpuInfo.getWait());
        cpuInfo.setFree(oshiCpuInfo.getFree());

        Sensors sensors = OshiUtil.getSensors();
        cpuInfo.setTemperature(sensors.getCpuTemperature());
        cpuInfo.setVoltage(sensors.getCpuVoltage());

        GlobalMemory globalMemory = OshiUtil.getMemory();
        MemoryInfo memory = new MemoryInfo();
        systemInfo.setMemory(memory);
        memory.setName("物理内存");
        memory.setTotal(CapacityUtil.mb(globalMemory.getTotal()));
        memory.setMax(memory.getTotal());
        memory.setFree(CapacityUtil.mb(globalMemory.getAvailable()));
        memory.setUsed(memory.getTotal() - memory.getFree());

        VirtualMemory virtualMemory = globalMemory.getVirtualMemory();
        MemoryInfo virtual = new MemoryInfo();
        systemInfo.setVirtual(virtual);
        virtual.setName("虚拟内存");
        virtual.setTotal(CapacityUtil.mb(virtualMemory.getSwapTotal()));
        virtual.setMax(virtual.getTotal());
        virtual.setUsed(CapacityUtil.mb(virtualMemory.getSwapUsed()));
        virtual.setFree(virtual.getTotal() - virtual.getUsed());

        DiskInfo totalDisk = new DiskInfo();
        totalDisk.setName("总磁盘");
        totalDisk.setTotal(0);
        totalDisk.setFree(0);
        OshiUtil.getHardware().getDiskStores().forEach(ds -> {
            totalDisk.setRead(totalDisk.getRead() + CapacityUtil.mb(ds.getReadBytes()));
            totalDisk.setWrite(totalDisk.getWrite() + CapacityUtil.mb(ds.getWriteBytes()));
            totalDisk.setTransferTime(totalDisk.getTransferTime() + ds.getTransferTime());
        });

        OshiUtil.getOs().getFileSystem().getFileStores().stream()
                .filter(fs -> StrUtil.isNotBlank(fs.getType()))
                .forEach(fs -> {
                    DiskInfo disk = new DiskInfo();
                    disk.setName(fs.getName().replace("本地固定", ""));
                    disk.setTotal(CapacityUtil.gb(fs.getTotalSpace()));
                    disk.setFree(CapacityUtil.gb(fs.getFreeSpace()));
                    disk.setUsed(disk.getTotal() - disk.getFree());
                    totalDisk.setTotal(totalDisk.getTotal() + disk.getTotal());
                    totalDisk.setFree(totalDisk.getFree() + disk.getFree());
                    systemInfo.getPartitionList().add(disk);
                });
        totalDisk.setUsed(totalDisk.getTotal() - totalDisk.getFree());
        systemInfo.setDisk(totalDisk);

        OshiUtil.getNetworkIFs().forEach(temp -> {
            NetworkIF network = new NetworkIF();
            network.setName(temp.getName());
            network.setDisplayName(temp.getDisplayName());
            network.setSpeed(temp.getSpeed());
            network.setRecv(CapacityUtil.kb(temp.getBytesRecv()));
            network.setSent(CapacityUtil.kb(temp.getBytesSent()));
            network.setPacketRecv(temp.getPacketsRecv());
            network.setPacketSent(temp.getPacketsSent());
            network.setAlias(temp.getIfAlias());
            network.setMac(temp.getMacaddr());
            network.setIpv4(temp.getIPv4addr());
            systemInfo.getNetworkIFList().add(network);
            systemInfo.setRecv(systemInfo.getRecv() + network.getRecv());
            systemInfo.setSent(systemInfo.getSent() + network.getSent());
        });

        systemInfo.setNow(LocalTime.now());
        return systemInfo;
    }
}
