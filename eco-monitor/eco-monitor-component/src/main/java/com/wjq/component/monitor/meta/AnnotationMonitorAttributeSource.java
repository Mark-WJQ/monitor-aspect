package com.wjq.component.monitor.meta;

import org.springframework.aop.support.AopUtils;
import org.springframework.util.ClassUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * 监控注解数据源
 *
 * @author wangjianqiang24
 * @date 2020/5/29
 */
public class AnnotationMonitorAttributeSource implements MonitorAttributeSource {

    /**
     * 转换器集合
     */
    List<MonitorAnnotationParser> annotationParsers;

    public AnnotationMonitorAttributeSource(List<MonitorAnnotationParser> annotationParsers) {
        this.annotationParsers = annotationParsers;
    }


    /**
     * 获取属性
     *
     * @param method
     * @param target
     * @return
     */
    @Override
    public MonitorAttribute getMonitorAttribute(Method method, Class<?> target) {
        Tuple tuple = computeMonitorAttribue(method, target);
        MonitorAttribute attribute = tuple.getAttribute();
        return attribute;
    }


    private Tuple computeMonitorAttribue(Method method, Class<?> target) {
        Tuple tuple = new Tuple();

        //先从方法查找，向上查找父类接口
        Method specificMethod = AopUtils.getMostSpecificMethod(method, target);
        MonitorAttribute attribute = findMonitorAttribute(specificMethod);
        if (Objects.nonNull(attribute)) {
            tuple.setElement(specificMethod);
            tuple.setAttribute(attribute);
            return tuple;
        }
        //从类上查找
        attribute = findMonitorAttribute(target);

        if (Objects.nonNull(attribute) && ClassUtils.isUserLevelMethod(method)) {
            tuple.setElement(target);
            tuple.setAttribute(attribute);
            return tuple;
        }

        if (specificMethod != method) {
            // Fallback is to look at the original method.
            attribute = findMonitorAttribute(method);
            if (Objects.nonNull(attribute)) {
                tuple.setElement(method);
                tuple.setAttribute(attribute);
                return tuple;
            }
            // Last fallback is the class of the original method.
            attribute = findMonitorAttribute(method.getDeclaringClass());
            if (Objects.nonNull(attribute) && ClassUtils.isUserLevelMethod(method)) {
                tuple.setElement(method.getDeclaringClass());
                tuple.setAttribute(attribute);
                return tuple;
            }
        }
        return tuple;
    }

    /**
     * 查找类上的监控属性
     *
     * @param target
     * @return
     */
    protected MonitorAttribute findMonitorAttribute(Class<?> target) {
        MonitorAttribute attribute = determineMonitorAttribute(target);
        return attribute;
    }

    /**
     * 获取给定属性上的注解属性
     *
     * @param element
     * @return
     */
    private MonitorAttribute determineMonitorAttribute(AnnotatedElement element) {
        for (MonitorAnnotationParser parser : annotationParsers) {
            MonitorAttribute attribute = parser.parserMonitorAnnotation(element);
            if (Objects.nonNull(attribute)) {
                return attribute;
            }
        }
        return null;
    }

    /**
     * 查找方法上的注解属性
     *
     * @param method
     * @return
     */
    protected MonitorAttribute findMonitorAttribute(Method method) {
        return determineMonitorAttribute(method);
    }


    /**
     * 二元类
     */
    private class Tuple {
        AnnotatedElement element;

        MonitorAttribute attribute;


        public AnnotatedElement getElement() {
            return element;
        }

        public void setElement(AnnotatedElement element) {
            this.element = element;
        }

        public MonitorAttribute getAttribute() {
            return attribute;
        }

        public void setAttribute(MonitorAttribute attribute) {
            this.attribute = attribute;
        }
    }
}
