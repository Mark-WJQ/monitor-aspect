package com.jd.jr.eco.component.monitor.meta;

import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 具有缓存性质的属性源,装饰器使用
 *
 * @author wangjianqiang24
 * @date 2020/7/27
 */
public class CachedAttributeSource implements MonitorAttributeSource {


    /**
     * 被装饰数据源
     */
    private MonitorAttributeSource monitorAttributeSource;

    public CachedAttributeSource(MonitorAttributeSource monitorAttributeSource) {
        this.monitorAttributeSource = monitorAttributeSource;
    }

    private Map<Object, MonitorAttribute> cache = new ConcurrentHashMap<>();

    static final MonitorAttribute NULL_MONITOR_ATTRIBUTE = new DefaultMonitorAttribute() {
        @Override
        public String toString() {
            return "null";
        }
    };

    /**
     * 获取监控属性
     *
     * @param method
     * @param target
     * @return
     */
    @Override
    public MonitorAttribute getMonitorAttribute(Method method, Class<?> target) {
        Object cacheKey = getCacheKey(method, target);
        MonitorAttribute attribute = this.cache.get(cacheKey);
        if (Objects.nonNull(attribute)) {
            if (attribute == NULL_MONITOR_ATTRIBUTE) {
                return null;
            }
            return attribute;
        }
        synchronized (cacheKey) {
            attribute = this.cache.get(cacheKey);
            if (Objects.isNull(attribute)) {
                attribute = monitorAttributeSource.getMonitorAttribute(method, target);
                setAttr(cacheKey, attribute);
            }
        }
        return attribute;
    }

    private Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }


    private void setAttr(Object cacheKey, MonitorAttribute attribute) {
        if (Objects.isNull(attribute)) {
            attribute = NULL_MONITOR_ATTRIBUTE;
        }
        this.cache.put(cacheKey, attribute);
    }

}
