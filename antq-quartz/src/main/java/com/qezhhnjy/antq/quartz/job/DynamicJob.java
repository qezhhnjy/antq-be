package com.qezhhnjy.antq.quartz.job;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.qezhhnjy.antq.quartz.entity.form.JobInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import java.lang.reflect.Method;

/**
 * @author zhaoyangfu
 * @date 2022/3/26-22:16
 */
@Slf4j
public class DynamicJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap dataMap = context.getMergedJobDataMap();
        String className = dataMap.get(JobInfo.DataTag.CLASS.name()).toString();
        String methodName = dataMap.get(JobInfo.DataTag.METHOD.name()).toString();
        String arg = dataMap.get(JobInfo.DataTag.ARG.name()).toString();
        Object bean = SpringUtil.getBean(ClassUtil.loadClass(className));
        Method method = ReflectUtil.getMethodByName(bean.getClass(), methodName);
        if (StrUtil.isBlank(arg)) ReflectUtil.invoke(bean, method);
        else {
            Class<?>[] params = method.getParameterTypes();
            Object argValue = null;
            Class<?> param = params[0];
            if (param == String.class) {
                argValue = arg;
            } else if (param == Integer.class) {
                argValue = Integer.valueOf(arg);
            } else if (param == Long.class) {
                argValue = Long.valueOf(arg);
            } else if (param == Float.class) {
                argValue = Float.valueOf(arg);
            } else if (param == Double.class) {
                argValue = Double.valueOf(arg);
            } else if (param == Boolean.class) {
                argValue = Boolean.valueOf(arg);
            } else if (param == Short.class) {
                argValue = Short.valueOf(arg);
            } else if (param == Byte.class) {
                argValue = Byte.valueOf(arg);
            }
            ReflectUtil.invoke(bean, method, argValue);
        }
    }

}
