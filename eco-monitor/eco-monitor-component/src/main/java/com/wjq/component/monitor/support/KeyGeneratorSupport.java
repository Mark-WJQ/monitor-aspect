package com.wjq.component.monitor.support;

import com.wjq.component.monitor.meta.MonitorDefinition;

/**
 * 唯一标识计算
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public interface KeyGeneratorSupport {

    /**
     * 开始计算
     * @param invocation 切面方法
     * @param definition 监控定义
     * @return
     */
    String calculate(SupportParam invocation, MonitorDefinition definition);

}
