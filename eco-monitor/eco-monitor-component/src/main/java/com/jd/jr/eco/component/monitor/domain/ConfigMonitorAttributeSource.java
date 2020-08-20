package com.jd.jr.eco.component.monitor.domain;

import com.jd.jr.eco.component.monitor.meta.MonitorConfig;
import com.jd.jr.eco.component.monitor.support.AttributeSourceSupport;
import com.jd.jr.eco.component.monitor.support.KeyGeneratorSupport;
import org.springframework.util.Assert;

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

    private AttributeSourceSupport attributeSourceSupport;

    public ConfigMonitorAttributeSource(MonitorConfig monitorConfig,AttributeSourceSupport attributeSourceSupport) {
        Assert.notNull(monitorConfig, "监控配置为空请检查");
        Assert.hasLength(monitorConfig.getAppName(), "监控appName为空");
        Assert.hasLength(monitorConfig.getKeyGenerator(),"key生成器未指定");
        this.monitorConfig = monitorConfig;
        this.attributeSourceSupport = attributeSourceSupport;
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
        monitorAttribute.setKeyGenerator(attributeSourceSupport.getBean(monitorConfig.getKeyGenerator(), KeyGeneratorSupport.class));
        return monitorAttribute;
    }

}
