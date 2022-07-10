package com.qezhhnjy.antq.flowable.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author zhaoyangfu
 * @since 2022/6/16-15:35
 */
@Slf4j
public class AskForLeaveFail implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        String name = execution.getVariable("name").toString();
        log.error("[{}]{} 请假失败...", execution.getProcessInstanceId(), name);
    }
}