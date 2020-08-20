package com.jd.jr.eco.component.monitor.support;

import com.jd.jr.eco.component.monitor.domain.MonitorDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * springel 表达式计算
 * 只使用请求参数来替换注解中的占位符
 * 格式为：#{[n]}
 * n为第几个入参
 * 如果该参数中有属性id则可以 #{[n].id},其他属性亦可，完全el表达式
 *
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class SpringELKeyGeneratorSupport implements KeyGeneratorSupport {

    private static Logger logger = LoggerFactory.getLogger(SpringELKeyGeneratorSupport.class);
    private static ExpressionParser parser = new SpelExpressionParser();
    private static ParserContext parserContext = new TemplateParserContext();
    private static Map<String, Expression> EXP_CACHE = new ConcurrentHashMap<>();

    /**
     * 开始计算
     *
     * @param invocation 切面方法
     * @param definition 监控定义
     * @return 如果替换失败，则返回null
     */
    @Override
    public String calculate(SupportParam invocation, MonitorDefinition definition) {
        try {
            Object[] args = invocation.getArgs();
            Expression expression = EXP_CACHE.get(definition.getKey());
            if (expression == null) {
                // 创建解析器
                expression = parser.parseExpression(definition.getKey(), parserContext);
                EXP_CACHE.put(definition.getKey(), expression);
            }
            //设置解析上下文(有哪些占位荷,以及每种占位符的值-根据方法的参数名和参数值
            String key = expression.getValue(args).toString();
            return key;
        } catch (Exception e) {
            logger.error("calculate monitor key error:{}", e);
            return null;
        }
    }


}
