package com.qezhhnjy.antq.quartz.service.impl;

import com.qezhhnjy.antq.quartz.AntqQuartzApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;

import javax.annotation.Resource;

@Slf4j
class JobServiceImplTest extends AntqQuartzApplicationTests {

    @Resource
    private Scheduler scheduler;

    @Test
    void addJob() throws Exception {
    }
}