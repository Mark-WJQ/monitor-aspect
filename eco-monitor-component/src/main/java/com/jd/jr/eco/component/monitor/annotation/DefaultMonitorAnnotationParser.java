package com.jd.jr.eco.component.monitor.annotation;

import com.jd.jr.eco.component.monitor.domain.DefaultMonitorAttribute;
import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;
import com.jd.jr.eco.component.monitor.domain.ProfEnum;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.lang.reflect.AnnotatedElement;

/**
 * 默认的注解 {@link Monitor}的转换器
 *
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class DefaultMonitorAnnotationParser implements MonitorAnnotationParser {


    /**
     * 转换监控属性 从 给定的类或方法上
     * 返回 {@code null} 如果方法/类 上没找到相应的注解
     *
     * @param element
     * @return 监控属性 或 {@code null}
     */
    @Override
    public MonitorAttribute parserMonitorAnnotation(AnnotatedElement element) {

        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
                element, Monitor.class, false, false);
        if (attributes != null) {
            return parseMonitorAnnotation(attributes);
        } else {
            return null;
        }
    }


    /**
     * 转换监控属性
     * @param attributes
     * @return
     */
    protected MonitorAttribute parseMonitorAnnotation(AnnotationAttributes attributes) {

        DefaultMonitorAttribute monitorAttribute = new DefaultMonitorAttribute();
        monitorAttribute.setManual(attributes.getBoolean("manual"));
        monitorAttribute.setAlarmCodes(attributes.getStringArray("alarmCodes"));
        monitorAttribute.setAlarms((Class<? extends Throwable>[]) attributes.getClassArray("alarms"));
        monitorAttribute.setErrors((Class<? extends Throwable>[]) attributes.getClassArray("errors"));
        monitorAttribute.setErrorCodes(attributes.getStringArray("errorCodes"));
        monitorAttribute.setIngoreCodes(attributes.getStringArray("ingoreCodes"));
        monitorAttribute.setIngoreErrors((Class<? extends Throwable>[]) attributes.getClassArray("ingoreErrors"));
        String key = attributes.getString("key");
        monitorAttribute.setKey(key);
        monitorAttribute.setMergeConfig(attributes.getBoolean("mergeConfig"));
        monitorAttribute.setProfEnums((ProfEnum[]) attributes.get("profEnums"));
        return monitorAttribute;
    }
}
