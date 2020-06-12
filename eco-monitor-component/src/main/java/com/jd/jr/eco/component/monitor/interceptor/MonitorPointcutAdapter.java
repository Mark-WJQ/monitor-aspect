package com.jd.jr.eco.component.monitor.interceptor;

import org.springframework.aop.Pointcut;

/**
 * pointcut 适配器
 * @author wangjianqiang24
 * @date 2020/5/26
 */
public class MonitorPointcutAdapter {

    private Pointcut pointcut;

    public MonitorPointcutAdapter(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }
}
