package com.qezhhnjy.antq.quartz.service;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.quartz.entity.JobAndTrigger;
import com.qezhhnjy.antq.quartz.entity.JobInfo;
import org.quartz.SchedulerException;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:24
 */
public interface JobService {
    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数 {@link JobInfo}
     * @throws Exception 异常
     */
    void addJob(JobInfo info) throws Exception;

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobInfo}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobAndTrigger jobAndTrigger) throws SchedulerException;

    /**
     * 暂停定时任务
     *
     * @param form 表单参数 {@link JobInfo}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobAndTrigger jobAndTrigger) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param form 表单参数 {@link JobInfo}
     * @throws SchedulerException 异常
     */
    void resumeJob(JobAndTrigger jobAndTrigger) throws SchedulerException;

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobInfo}
     * @throws Exception 异常
     */
    void cronJob(JobAndTrigger jobAndTrigger) throws Exception;

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    PageInfo<JobAndTrigger> query(Query query);

    void test(String name);
}
