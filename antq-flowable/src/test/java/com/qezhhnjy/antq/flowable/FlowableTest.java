package com.qezhhnjy.antq.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyangfu
 * @since 2022/6/16-15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FlowableTest {

    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService    taskService;

    String staffId = "10098";
    String leaderId = "10016";
    String managerId = "10090";

    /**
     * 开启一个流程
     */
    @Test
    public void askForLeave() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", staffId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ask-for-leave", map);
        runtimeService.setVariable(processInstance.getId(), "name", "qezhhnjy");
        runtimeService.setVariable(processInstance.getId(), "reason", "休息一下");
        runtimeService.setVariable(processInstance.getId(), "days", 10);
        log.info("创建请假流程 processId：{}", processInstance.getId());
    }

    /**
     * 提交给主管审批
     */
    @Test
    public void submitToLeader() {
        //员工查找到自己的任务，然后提交给组长审批
        List<Task> list = taskService.createTaskQuery().taskAssignee(staffId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("任务 ID：{}；任务处理人：{}；任务是否挂起：{}", task.getId(), task.getAssignee(), task.isSuspended());
            Map<String, Object> map = new HashMap<>();
            //提交给组长的时候，需要指定组长的 id
            map.put("username", leaderId);
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 主管审核通过
     */
    @Test
    public void leaderApprove() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(leaderId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是同意，需要指定经理的 id
            map.put("username", managerId);
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 主管审核不通过
     */
    @Test
    public void leaderReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(leaderId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("组长 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            //组长审批的时候，如果是拒绝，就不需要指定经理的 id
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 经理审批自己的任务-批准
     */
    @Test
    public void managerApprove() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "通过");
            taskService.complete(task.getId(), map);
        }
    }

    /**
     * 经理审批自己的任务-拒绝
     */
    @Test
    public void managerReject() {
        List<Task> list = taskService.createTaskQuery().taskAssignee(managerId).orderByTaskId().desc().list();
        for (Task task : list) {
            log.info("经理 {} 在审批 {} 任务", task.getAssignee(), task.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("checkResult", "拒绝");
            taskService.complete(task.getId(), map);
        }
    }

    @Test
    public void success() {
        askForLeave();
        submitToLeader();
        leaderApprove();
        managerApprove();
    }

    @Test
    public void leaderFail() {
        askForLeave();
        submitToLeader();
        leaderReject();
    }

    @Test
    public void ManagerFail() {
        askForLeave();
        submitToLeader();
        leaderApprove();
        managerReject();
    }
}
