package com.jd.jr.eco.component.monitor.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * @author wangjianqiang24
 * @date 2020/7/27
 */
public class AttributeSourceSupport implements ApplicationContextAware {


    private static Logger logger = LoggerFactory.getLogger(AttributeSourceSupport.class);

    private ApplicationContext applicationContext;

    public static final KeyCalculater NON_CALCULATER = (kp, def) -> {
        logger.debug("don't cal anything,key:{}", def.getKey());
        return def.getKey();
    };

    /**
     * 获取计算可以的算法
     *
     * @param keyCalculater
     * @return 如果根据给定的name在springcontext 中可以找到相应的bean则直接返回，如果没有则返回一个自定义的不执行任何逻辑的计算器
     */
    public KeyCalculater getKeyCalculater(String keyCalculater) {
        if (!StringUtils.hasText(keyCalculater) || !applicationContext.containsBean(keyCalculater)) {
            logger.warn("the key :{} of calculater can't find in context", keyCalculater);
            return NON_CALCULATER;
        }
        KeyCalculater calculater = applicationContext.getBean(keyCalculater, KeyCalculater.class);
        return calculater;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
