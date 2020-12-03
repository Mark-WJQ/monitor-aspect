package com.wjq.component.monitor.meta;

import java.lang.reflect.Method;

/**
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class CompositeMonitorAttributeSource implements MonitorAttributeSource {



    private final MonitorAttributeSource[] monitorAttributeSources;

    public CompositeMonitorAttributeSource(MonitorAttributeSource... monitorAttributeSources) {
        this.monitorAttributeSources = monitorAttributeSources;
    }

    public MonitorAttributeSource[] getMonitorAttributeSources() {
        return this.monitorAttributeSources;
    }

    /**
     * 获取监控属性
     *
     * @param method
     * @param target
     * @return
     */
    @Override
    public MonitorAttribute getMonitorAttribute(Method method, Class<?> target) {
        for (MonitorAttributeSource source : this.monitorAttributeSources){
            MonitorAttribute attribute = source.getMonitorAttribute(method,target);
            if (attribute != null){
                return attribute;
            }
        }
        return null;
    }
}
