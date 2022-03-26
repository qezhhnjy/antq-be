package com.qezhhnjy.antq.quartz.job;

import com.qezhhnjy.antq.quartz.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;

/**
 * <p>
 * Hello Job
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:22
 */
@Slf4j
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        log.error("Hello Job 执行时间: {}", LocalDateTime.now());
    }
}
