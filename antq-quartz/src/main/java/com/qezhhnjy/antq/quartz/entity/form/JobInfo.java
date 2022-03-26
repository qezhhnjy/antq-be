package com.qezhhnjy.antq.quartz.entity.form;

import lombok.Data;
import lombok.experimental.Accessors;
import org.quartz.JobDataMap;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 定时任务详情
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-26 13:42
 */
@Data
@Accessors(chain = true)
public class JobInfo {

    /**
     * 定时任务全类名
     */
    @NotBlank(message = "Job类名不能为空")
    private String jobClassName;

    @NotBlank(message = "执行类名不能为空")
    private String className;

    @NotBlank(message = "方法名不能为空")
    private String methodName;

    private String arg;

    /**
     * 任务组名
     */
    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    public String identify() {
        return String.format("%s-%s.%s(%s)", jobClassName, className, methodName, arg);
    }

    public JobDataMap data() {
        JobDataMap data = new JobDataMap();
        data.put(DataTag.CLASS.name(), className);
        data.put(DataTag.METHOD.name(), methodName);
        data.put(DataTag.ARG.name(), arg);
        return data;
    }

    public enum DataTag {
        CLASS, METHOD, ARG
    }
}
