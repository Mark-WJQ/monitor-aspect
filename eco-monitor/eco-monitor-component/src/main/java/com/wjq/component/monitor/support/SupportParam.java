package com.wjq.component.monitor.support;

import java.lang.reflect.Method;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class SupportParam {

    public SupportParam(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    public SupportParam(Method method, Class target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    private Method method;

    private Class target;

    private Object[] args;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class getTarget() {
        return target;
    }

    public void setTarget(Class target) {
        this.target = target;
    }
}
