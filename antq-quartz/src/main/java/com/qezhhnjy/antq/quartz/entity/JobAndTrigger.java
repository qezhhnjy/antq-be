package com.qezhhnjy.antq.quartz.entity;

import lombok.Data;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

/**
 * <p>
 * 实体类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 15:05
 */
@Data
public class JobAndTrigger {
    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 定时任务组
     */
    private String jobGroup;
    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 重复间隔
     */
    private Integer repeatInterval;
    /**
     * 触发次数
     */
    private Integer timesTriggered;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 时区
     */
    private String timeZoneId;
    /**
     * 定时任务状态
     */
    private String triggerState;

    public JobKey jobKey() {
        return JobKey.jobKey(jobName, jobGroup);
    }

    public TriggerKey triggerKey() {
        return TriggerKey.triggerKey(triggerName, triggerGroup);
    }
}
