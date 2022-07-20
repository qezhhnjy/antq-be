package com.qezhhnjy.antq.easy.rules.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-17:41
 */
@Rule(name = "my rule", description = "my rule description", priority = 1)
public class AnnotationRule {

    @Condition
    public boolean when(@Fact("a") String fact) {
        // 规则条件
        System.out.println("AnnotationRule" + fact);
        return true;
    }

    @Action(order = 1)
    public void then(Facts facts) throws Exception {
        // 规则为true时的操作1
        for (org.jeasy.rules.api.Fact<?> fact : facts) {
            System.out.println("AnnotationRule" + fact);
        }
    }

    @Action(order = 2)
    public void end() throws Exception {
        // 规则为true时的操作2
    }
}
