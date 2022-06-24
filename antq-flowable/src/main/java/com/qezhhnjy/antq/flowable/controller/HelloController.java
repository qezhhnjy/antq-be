package com.qezhhnjy.antq.flowable.controller;

import cn.hutool.core.map.MapUtil;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Model;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyangfu
 * @since 2022/6/16-15:15
 */
@RestController
public class HelloController {

    @Resource
    private RuntimeService    runtimeService;
    @Resource
    private TaskService       taskService;
    @Resource
    private RepositoryService repositoryService;
    @Resource
    private ProcessEngine     processEngine;
    @Resource
    private HistoryService    historyService;

    @GetMapping("/pic")
    public void showPic(HttpServletResponse resp, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
        if (pi == null) {
            return;
        }
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(processId)
                .list();

        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        // 生成流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engineConfiguration = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engineConfiguration.getProcessDiagramGenerator();
        byte[] buf = new byte[1024];
        int length;
        try (InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows,
                engineConfiguration.getActivityFontName(), engineConfiguration.getLabelFontName(),
                engineConfiguration.getAnnotationFontName(), engineConfiguration.getClassLoader(), 1.0, false);
             OutputStream out = resp.getOutputStream()) {
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
        }
    }

    @GetMapping("/all")
    public Object all() {
        List<Task> list = taskService.createTaskQuery().list();
        List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().list();
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
        List<Model> modelList = repositoryService.createModelQuery().list();
        return MapUtil.builder()
                .put("task", list)
                .put("process", processInstanceList)
                .put("history", historicProcessInstances)
                .put("model", modelList)
                .build();
    }
}