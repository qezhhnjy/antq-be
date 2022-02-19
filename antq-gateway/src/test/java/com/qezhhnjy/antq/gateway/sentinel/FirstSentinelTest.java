package com.qezhhnjy.antq.gateway.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FirstSentinelTest {

    @Resource
    private FirstSentinel firstSentinel;

    @Test
    void first() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(firstSentinel.first());
        }
    }
}