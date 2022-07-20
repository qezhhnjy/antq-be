package com.qezhhnjy.antq.easy.rules;

import com.qezhhnjy.antq.easy.rules.rules.AnnotationRule;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-17:11
 */
@SpringBootApplication
public class EasyRulesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyRulesApplication.class, args);
        Rule rule = new RuleBuilder().priority(3)
                .when(new Condition() {
                    @Override
                    public boolean evaluate(Facts facts) {
                        for (Fact<?> fact : facts) {
                            System.out.println(fact);
                        }
                        return true;
                    }
                }).then(new Action() {
                    @Override
                    public void execute(Facts facts) throws Exception {
                        for (Fact<?> fact : facts) {
                            System.out.println(";;;;" + fact);
                        }
                    }
                }).build();

        DefaultRulesEngine engine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.add(new Fact<>("a", "b"));
        engine.fire(new Rules(rule,new AnnotationRule()), facts);
    }
}
