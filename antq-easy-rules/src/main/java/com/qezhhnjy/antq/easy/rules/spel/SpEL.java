package com.qezhhnjy.antq.easy.rules.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyangfu
 * @since 2022/7/20-18:47
 */
@Component
public class SpEL {

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
        SpEL spEL = new SpEL();
        ctx.setVariable("bean_id", spEL);

        //setRootObject并非必须；一个EvaluationContext只能有一个RootObject，引用它的属性时，可以不加前缀
        // ctx.setRootObject(XXX);

        //getValue有参数ctx，从新的容器中根据SpEL表达式获取所需的值
        // Object value = exp.getValue(ctx);
    }
    public void spel() {

    }
}
