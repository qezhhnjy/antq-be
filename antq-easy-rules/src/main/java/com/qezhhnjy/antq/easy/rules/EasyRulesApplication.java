package com.qezhhnjy.antq.easy.rules;

import com.qezhhnjy.antq.easy.rules.rules.AnnotationRule;
import com.qezhhnjy.antq.easy.rules.spel.Spel;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-17:11
 */
@SpringBootApplication
public class EasyRulesApplication implements CommandLineRunner {

    @Resource
    private Spel               spel;
    @Resource
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(EasyRulesApplication.class, args);
        Rule rule = new RuleBuilder()
                .when(facts -> {
                    for (Fact<?> fact : facts) {
                        System.out.println(fact);
                    }
                    return true;
                }).then(facts -> {
                    for (Fact<?> fact : facts) {
                        System.out.println(";;;;" + fact);
                    }
                }).build();

        DefaultRulesEngine engine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.add(new Fact<>("a", "b"));
        engine.fire(new Rules( new AnnotationRule(),rule), facts);

    }

    @Override
    public void run(String... args) throws Exception {
        // spel.spel();

/*        SpELRule rule = new SpELRule(new BeanFactoryResolver(context));
        rule.name("spel rule").description("spel rule desc").priority(1)
                .when("#{@spel.condition(#a)}")
                .then("#{@spel.test(#a)}");

        DefaultRulesEngine engine = new DefaultRulesEngine();
        Facts facts = new Facts();
        facts.add(new Fact<>("a", "b"));
        engine.fire(new Rules(rule), facts);*/
    }
}
