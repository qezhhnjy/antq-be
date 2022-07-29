package com.qezhhnjy.antq.easy.rules.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-18:47
 */
@Component
public class Spel {

    @Resource
    private ApplicationContext ctx;

    public static void main(String[] args) {
        //创建ExpressionParser解析表达式
        ExpressionParser parser = new SpelExpressionParser();
        //表达式放置
        Expression exp = parser.parseExpression("表达式");
        //执行表达式，默认容器是spring本身的容器：ApplicationContext
        Object value = exp.getValue();

        /**如果使用其他的容器，则用下面的方法*/
        //创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        //向容器内添加bean
        Spel spEL = new Spel();
        ctx.setVariable("bean_id", spEL);

        //setRootObject并非必须；一个EvaluationContext只能有一个RootObject，引用它的属性时，可以不加前缀
        // ctx.setRootObject(XXX);

        //getValue有参数ctx，从新的容器中根据SpEL表达式获取所需的值
        // Object value = exp.getValue(ctx);
    }

    public String test(String a) {
        System.out.println("test " + a);
        return "test " + a;
    }

    public boolean condition(String c) {
        System.out.println(true + " " + c);
        return true;
    }

    public void spel() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.setRootObject(ctx);
        System.out.println(parser.parseExpression("#root").getValue(context));
        System.out.println(parser.parseExpression("getBean('spel')").getValue(context));
        System.out.println(parser.parseExpression("getBean('spel').test('Amy')").getValue(context));

        context.setBeanResolver(new BeanFactoryResolver(ctx));
        System.out.println(parser.parseExpression("@spel").getValue(context));
        System.out.println(parser.parseExpression("@spel.test('Amy')").getValue(context));
    }
}
