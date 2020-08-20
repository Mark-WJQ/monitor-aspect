package com.jd.jr.eco.component.monitor.meta;

import com.jd.jr.eco.component.monitor.support.AttributeSourceSupport;
import com.jd.jr.eco.component.monitor.support.KeyGeneratorSupport;
import com.jd.jr.eco.component.monitor.support.ResultConverterSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.AnnotatedElement;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 默认的注解 {@link Monitor}的转换器
 *
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class DefaultMonitorAnnotationParser implements MonitorAnnotationParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultMonitorAnnotationParser.class);

    private AttributeSourceSupport attributeSourceSupport;

    private MonitorConfig monitorConfig;

    /**
     * 转换监控属性 从 给定的类或方法上
     * 返回 {@code null} 如果方法/类 上没找到相应的注解
     *
     * @param element
     * @return 监控属性 或 {@code null}
     */
    @Override
    public MonitorAttribute parserMonitorAnnotation(AnnotatedElement element) {
        Monitor monitor = AnnotatedElementUtils.findMergedAnnotation(element, Monitor.class);
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
        monitorAttribute.setProfEnums(monitor.profEnums());
        monitorAttribute.setKeyGenerator(attributeSourceSupport.getBean(monitor.keyGenerator(), KeyGeneratorSupport.class));
        monitorAttribute.setResultConverter(attributeSourceSupport.getBean(monitor.resultConverter(), ResultConverterSupport.class));
        monitorAttribute.setAppName(monitorConfig.getAppName());
        if (monitor.mergeConfig()) {
            mergeAttribute(monitorAttribute);
        }
        return monitorAttribute;

    }

    @Autowired
    public void setAttributeSourceSupport(AttributeSourceSupport attributeSourceSupport) {
        this.attributeSourceSupport = attributeSourceSupport;
    }

    @Autowired
    public void setMonitorConfig(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
    }

    /**
     * 合并公共配置,简单的覆盖配置
     *
     * @param attribute
     */
    protected void mergeAttribute(DefaultMonitorAttribute attribute) {
        DefaultMonitorAttribute defaultMonitorAttribute = attribute;
        if (isEmpty(attribute.getAlarmCodes()) && !isEmpty(monitorConfig.getAlarmCodes())) {
            defaultMonitorAttribute.setAlarmCodes(monitorConfig.getAlarmCodes());
        }

        if (isEmpty(attribute.getErrorCodes()) && !isEmpty(monitorConfig.getErrorCodes())) {
            defaultMonitorAttribute.setErrorCodes(monitorConfig.getErrorCodes());
        }

        if (isEmpty(attribute.getIngoreCodes()) && !isEmpty(monitorConfig.getIngoreCodes())) {
            defaultMonitorAttribute.setIngoreCodes(monitorConfig.getIngoreCodes());
        }

        if (isEmpty(attribute.getAlarms()) && !isEmpty(monitorConfig.getAlarmExceptions())) {
            defaultMonitorAttribute.setAlarms(monitorConfig.getAlarmExceptions());
        }

        if (isEmpty(attribute.getErrors()) && !isEmpty(monitorConfig.getErrorExceptions())) {
            defaultMonitorAttribute.setErrors(monitorConfig.getErrorExceptions());
        }

        if (isEmpty(attribute.getIngoreErrors()) && !isEmpty(monitorConfig.getIngoreExceptions())) {
            defaultMonitorAttribute.setIngoreErrors(monitorConfig.getIngoreExceptions());
        }
    }


}
