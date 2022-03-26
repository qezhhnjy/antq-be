package com.qezhhnjy.antq.quartz.mapper;

import com.qezhhnjy.antq.quartz.entity.JobAndTrigger;

import java.util.List;

/**
 * <p>
 * Job Mapper
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 15:12
 */
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
}
