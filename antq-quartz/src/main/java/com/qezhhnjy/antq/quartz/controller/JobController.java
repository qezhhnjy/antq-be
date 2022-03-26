package com.qezhhnjy.antq.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.common.query.Query;
import com.qezhhnjy.antq.quartz.entity.JobAndTrigger;
import com.qezhhnjy.antq.quartz.entity.JobInfo;
import com.qezhhnjy.antq.quartz.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * Job Controller
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:23
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * 保存定时任务
     */
    @PostMapping("add")
    public BaseResult<?> addJob(@RequestBody @Valid JobInfo info) throws Exception {
        jobService.addJob(info);
        return BaseResult.success(info);
    }

    /**
     * 删除定时任务
     */
    @PostMapping("delete")
    public BaseResult<?> deleteJob(@RequestBody @Valid JobInfo info) throws SchedulerException {
        jobService.deleteJob(info);
        return BaseResult.success();
    }

    /**
     * 暂停定时任务
     */
    @PostMapping("pause")
    public BaseResult<?> pauseJob(@RequestBody @Valid JobInfo info) throws SchedulerException {
        jobService.pauseJob(info);
        return BaseResult.success();
    }

    /**
     * 恢复定时任务
     */
    @PostMapping("resume")
    public BaseResult<?> resumeJob(@RequestBody @Valid JobInfo info) throws SchedulerException {
        jobService.resumeJob(info);
        return BaseResult.success();
    }

    /**
     * 修改定时任务，定时时间
     */
    @PostMapping("cron")
    public BaseResult<?> cronJob(@RequestBody @Valid JobInfo info) throws Exception {
        jobService.cronJob(info);
        return BaseResult.success();
    }

    @PostMapping("list")
    public BaseResult<PageInfo<JobAndTrigger>> jobList(Query query) {
        PageInfo<JobAndTrigger> pageInfo = jobService.list(query);
        return BaseResult.success(pageInfo);
    }

}
