package com.qezhhnjy.antq.flowable.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author zhaoyangfu
 * @since 2022/6/16-17:48
 */
@Slf4j
public class AskForLeaveApprove implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        String name = execution.getVariable("name").toString();
        log.info("[{}]{} 请假成功...", execution.getProcessInstanceId(), name);

    }
}
