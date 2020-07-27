package com.jd.jr.eco.component.monitor.domain;

import com.jd.jr.eco.component.monitor.meta.MonitorConfig;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 配置读取监控信息
 *
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class ConfigMonitorAttributeSource implements MonitorAttributeSource {

    /**
     * 配置信息
     */
    private MonitorConfig monitorConfig;

    public ConfigMonitorAttributeSource(MonitorConfig monitorConfig) {
        Assert.notNull(monitorConfig, "监控配置为空请检查");
        Assert.hasLength(monitorConfig.getAppName(), "监控appName为空");
        this.monitorConfig = monitorConfig;
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
        DefaultMonitorAttribute monitorAttribute = new DefaultMonitorAttribute();
        monitorAttribute.setAlarmCodes(monitorConfig.getAlarmCodes());
        monitorAttribute.setAlarms(monitorConfig.getAlarmExceptions());
        monitorAttribute.setErrors(monitorConfig.getErrorExceptions());
        monitorAttribute.setErrorCodes(monitorConfig.getErrorCodes());
        monitorAttribute.setIngoreCodes(monitorConfig.getIngoreCodes());
        monitorAttribute.setIngoreErrors(monitorConfig.getIngoreExceptions());
        monitorAttribute.setAppName(monitorConfig.getAppName());
        monitorAttribute.setProfEnums(monitorConfig.getProfEnums());
        if (StringUtils.hasLength(monitorConfig.getKeyPre())) {
            String key = String.join(".", monitorConfig.getKeyPre(), method.getDeclaringClass().getSimpleName(), method.getName());
            monitorAttribute.setKey(key);
        } else {
            String key = String.join(".", method.getDeclaringClass().getName(), method.getName());
            monitorAttribute.setKey(key);
        };
        return monitorAttribute;
    }

}
