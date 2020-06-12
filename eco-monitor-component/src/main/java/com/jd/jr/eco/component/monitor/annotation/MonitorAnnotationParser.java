package com.jd.jr.eco.component.monitor.annotation;


import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 从类或方法注解上转换出监控属性
 * @author wangjianqiang24
 * @date 2020/6/1
 *
 * @see DefaultMonitorAnnotationParser
 */
public interface MonitorAnnotationParser {

    /**
     * 转换监控属性 从 给定的类或方法上
     * 返回 {@code null} 如果方法/类 上没找到相应的注解
     * @param element
     * @return 监控属性 或 {@code null}
     */
    MonitorAttribute parserMonitorAnnotation(AnnotatedElement element);

}
