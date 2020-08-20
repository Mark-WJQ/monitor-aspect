package com.jd.jr.eco.component.monitor.support;

import com.jd.jr.eco.component.monitor.domain.MonitorDefinition;
import com.jd.jr.eco.component.monitor.meta.MonitorConfig;
import org.springframework.util.StringUtils;

/**
 * @author wangjianqiang24
 * @date 2020/8/20
 */
public class DefaultKeyGeneratorSupport implements KeyGeneratorSupport {


    /**
     * 监控整体配置
     */
    private MonitorConfig monitorConfig;


    public DefaultKeyGeneratorSupport(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
    }

    /**
     * 开始计算
     *
     * @param param 方法，类信息
     * @param definition 监控定义
     * @return
     */
    @Override
    public String calculate(SupportParam param, MonitorDefinition definition) {
        if (StringUtils.isEmpty(definition.getKey())) {
            String keyPre = monitorConfig.getAppName();
            //todo 可以缓存减少计算
            String key = String.join(".", keyPre, param.getTarget().getSimpleName(), param.getMethod().getName());
            return key;
        }
        return definition.getKey();
    }

}
