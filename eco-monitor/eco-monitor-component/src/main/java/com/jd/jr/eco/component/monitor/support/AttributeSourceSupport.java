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

    /**
     * 获取计算key的算法
     *
     * @param keyCalculater
     * @return 如果根据给定的name在springcontext 中可以找到相应的bean则直接返回，如果没有则返回 null
     */
    public KeyCalculaterSupport getKeyCalculater(String keyCalculater) {
        if (!StringUtils.hasText(keyCalculater) || !applicationContext.containsBean(keyCalculater)) {
            logger.warn("the key :{} of calculater can't find in context", keyCalculater);
            return null;
        }
        KeyCalculaterSupport calculater = applicationContext.getBean(keyCalculater, KeyCalculaterSupport.class);
        return calculater;
    }


    /**
     * 获取转换器
     *
     * @param resultConverter
     * @return 如果根据给定的name在springcontext 中可以找到相应的bean则直接返回，如果没有则返回 null
     */
    public ResultConverterSupport getResultConverter(String resultConverter) {
        if (!StringUtils.hasText(resultConverter) || !applicationContext.containsBean(resultConverter)) {
            logger.warn("the key :{} of resultConverter can't find in context", resultConverter);
            return null;
        }
        ResultConverterSupport converter = applicationContext.getBean(resultConverter, ResultConverterSupport.class);
        return converter;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
