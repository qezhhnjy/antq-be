package com.qezhhnjy.antq.quartz.job;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.qezhhnjy.antq.quartz.entity.JobInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * @author zhaoyangfu
 * @date 2022/3/26-22:16
 */
@Slf4j
public class DynamicJob extends QuartzJobBean {

    @Override
    public void executeInternal(JobExecutionContext context) {
        JobDataMap dataMap = context.getMergedJobDataMap();
        String className = dataMap.get(JobInfo.DataTag.CLASS.name()).toString();
        String methodName = dataMap.get(JobInfo.DataTag.METHOD.name()).toString();
        String arg = dataMap.get(JobInfo.DataTag.ARG.name()).toString();
        Object bean = SpringUtil.getBean(ClassUtil.loadClass(className));
        Method method = ReflectUtil.getMethodByName(bean.getClass(), methodName);
        if (StrUtil.isBlank(arg)) ReflectUtil.invoke(bean, method);
        else {
            Class<?>[] params = method.getParameterTypes();
            Object argValue;
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
            } else {
                argValue = JSONUtil.toBean(arg, param);
            }
            ReflectUtil.invoke(bean, method, argValue);
        }
    }

}
