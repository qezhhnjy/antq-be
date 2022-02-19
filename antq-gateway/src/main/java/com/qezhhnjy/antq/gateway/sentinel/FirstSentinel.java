package com.qezhhnjy.antq.gateway.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyangfu
 * @date 2022/1/25-14:31
 */
@Slf4j
@Component
public class FirstSentinel {

    @PostConstruct
    public void initSentinelRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("queryGoodsInfo");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        flowRule.setCount(3);
        rules.add(flowRule);
        // FlowRuleManager.loadRules(rules);

        //熔断规则： 5s内调用接口出现异常次数超过5的时候, 进行熔断
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("queryGoodsInfo");
        degradeRule.setCount(200);

        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//熔断规则
        degradeRule.setTimeWindow(5);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }

    @SentinelResource("queryGoodsInfo")
    public String first() throws InterruptedException {
        Thread.sleep(1000);
        return "a";
    }
}
