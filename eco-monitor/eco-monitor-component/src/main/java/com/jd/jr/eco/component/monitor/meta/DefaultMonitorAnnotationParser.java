package com.jd.jr.eco.component.monitor.meta;

import com.jd.jr.eco.component.monitor.domain.DefaultMonitorAttribute;
import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;
import com.jd.jr.eco.component.monitor.support.AttributeSourceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.AnnotatedElement;

/**
 * 默认的注解 {@link Monitor}的转换器
 *
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class DefaultMonitorAnnotationParser implements MonitorAnnotationParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultMonitorAnnotationParser.class);

    private AttributeSourceSupport attributeSourceSupport;

    /**
     * 转换监控属性 从 给定的类或方法上
     * 返回 {@code null} 如果方法/类 上没找到相应的注解
     *
     * @param element
     * @return 监控属性 或 {@code null}
     */
    @Override
    public MonitorAttribute parserMonitorAnnotation(AnnotatedElement element) {
        Monitor monitor = AnnotatedElementUtils.findMergedAnnotation(element,Monitor.class);
        if (monitor != null) {
           return parseMonitorAnnotation(monitor);
        } else {
            return null;
        }
    }

    private MonitorAttribute parseMonitorAnnotation(Monitor monitor) {
        DefaultMonitorAttribute monitorAttribute = new DefaultMonitorAttribute();
        monitorAttribute.setSkip(monitor.skip());
        monitorAttribute.setAlarmCodes(monitor.alarmCodes());
        monitorAttribute.setAlarms(monitor.alarms());
        monitorAttribute.setErrors(monitor.errors());
        monitorAttribute.setErrorCodes(monitor.errorCodes());
        monitorAttribute.setIngoreCodes(monitor.ingoreCodes());
        monitorAttribute.setIngoreErrors(monitor.ingoreErrors());
        monitorAttribute.setKey(monitor.key());
        monitorAttribute.setMergeConfig(monitor.mergeConfig());
        monitorAttribute.setProfEnums(monitor.profEnums());
        monitorAttribute.setKeyCalculater(attributeSourceSupport.getKeyCalculater(monitor.keyCalculater()));
        monitorAttribute.setResultConverter(attributeSourceSupport.getResultConverter(monitor.resultConverter()));
        return monitorAttribute;

    }

    @Autowired
    public void setAttributeSourceSupport(AttributeSourceSupport attributeSourceSupport) {
        this.attributeSourceSupport = attributeSourceSupport;
    }
}
