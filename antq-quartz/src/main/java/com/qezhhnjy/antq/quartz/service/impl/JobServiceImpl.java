package com.qezhhnjy.antq.quartz.service.impl;

import cn.hutool.core.util.ClassUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.quartz.entity.JobAndTrigger;
import com.qezhhnjy.antq.quartz.entity.JobInfo;
import com.qezhhnjy.antq.quartz.mapper.JobMapper;
import com.qezhhnjy.antq.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * Job Service
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:25
 */
@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private JobMapper jobMapper;

    /**
     * 添加并启动定时任务
     */
    @Override
    public void addJob(JobInfo info) throws Exception {
        // 启动调度器
        scheduler.start();
        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(ClassUtil.loadClass(info.getJobClassName()))
                .withIdentity(info.jobKey())
                .setJobData(info.data())
                .build();
        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(info.getCronExpression());
        //根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(info.triggerKey())
                .withSchedule(cron)
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 删除定时任务
     */
    @Override
    public void deleteJob(JobInfo info) throws SchedulerException {
        TriggerKey triggerKey = info.triggerKey();
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(info.jobKey());
    }

    /**
     * 暂停定时任务
     */
    @Override
    public void pauseJob(JobInfo info) throws SchedulerException {
        scheduler.pauseJob(info.jobKey());
    }

    /**
     * 恢复定时任务
     */
    @Override
    public void resumeJob(JobInfo info) throws SchedulerException {
        scheduler.resumeJob(info.jobKey());
    }

    /**
     * 重新配置定时任务
     */
    @Override
    public void cronJob(JobInfo info) throws Exception {
        TriggerKey triggerKey = info.triggerKey();
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(info.getCronExpression());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 根据Cron表达式构建一个Trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 查询定时任务列表
     */
    @Override
    public PageInfo<JobAndTrigger> list(Query query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<JobAndTrigger> list = jobMapper.list();
        return new PageInfo<>(list);
    }

    @Override
    public void test(String name) {
        log.info("Quartz test name=>{}", name);
    }
}
