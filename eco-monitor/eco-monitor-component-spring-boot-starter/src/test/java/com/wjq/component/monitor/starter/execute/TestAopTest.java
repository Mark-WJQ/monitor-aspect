package com.wjq.component.monitor.starter.execute;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangjianqiang24
 * @date 2020/6/15
 */
@SpringBootTest(classes = TestAop.class)
@EnableAutoConfiguration
public class TestAopTest {

    @Autowired
    private TestAop testAop;

    @Autowired
    private ApplicationContext applicationContext;

    public static StandardEvaluationContext evalContext = new StandardEvaluationContext();

    private static ExpressionParser parser = new SpelExpressionParser();

    @Test
    public void monitor() {
        //testAop.monitor(Arrays.asList("11111","3333"));
        evalContext.addPropertyAccessor(new MapAccessor());
        evalContext.setBeanResolver(new BeanFactoryResolver(applicationContext));

        Map<String,Object> map = new HashMap<>();
        map.put("list", Arrays.asList("11111","3333"));
        Expression exp = parser.parseExpression("@testAop.monitor(list)");
        exp.getValue(evalContext,map);


    }
}