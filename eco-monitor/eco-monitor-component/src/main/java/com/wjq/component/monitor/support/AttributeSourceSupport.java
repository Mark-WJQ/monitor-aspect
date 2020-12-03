package com.wjq.component.monitor.support;

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
     * 获取
     * @param beanName bean名字
     * @param requiredType bean类型
     * @param <T> 范型
     * @return
     */
    public <T> T getBean(String beanName,Class<T> requiredType){
        if (!StringUtils.hasText(beanName) || !applicationContext.containsBean(beanName)) {
            logger.warn("the key :{} of bean can't find in context", beanName);
            return null;
        }
        T converter = applicationContext.getBean(beanName, requiredType);
        return converter;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
