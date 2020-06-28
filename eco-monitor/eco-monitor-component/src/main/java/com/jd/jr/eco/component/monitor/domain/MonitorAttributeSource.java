package com.jd.jr.eco.component.monitor.domain;

import java.lang.reflect.Method;

public interface MonitorAttributeSource {

    /**
     * 是否是潜在的被代理类
     * @param taget
     * @return
     */
    default boolean isCandidateClass(Class<?> taget){
        return true;
    }

    /**
     * 获取监控属性
     * @param method
     * @param target
     * @return
     */
     MonitorAttribute getMonitorAttribute(Method method, Class<?> target);
}
