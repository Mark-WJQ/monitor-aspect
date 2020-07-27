package com.jd.jr.eco.component.monitor.support;

import java.lang.reflect.Method;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class KeyCalParam {

    public KeyCalParam(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    private Method method;

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
}
