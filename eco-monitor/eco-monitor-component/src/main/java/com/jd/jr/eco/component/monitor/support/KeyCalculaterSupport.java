package com.jd.jr.eco.component.monitor.support;

import com.jd.jr.eco.component.monitor.domain.MonitorDefinition;

/**
 * 唯一标识计算
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public interface KeyCalculaterSupport {

    /**
     * 开始计算
     * @param invocation 切面方法
     * @param definition 监控定义
     * @return
     */
    String calculate(KeyCalParam invocation, MonitorDefinition definition);

}
