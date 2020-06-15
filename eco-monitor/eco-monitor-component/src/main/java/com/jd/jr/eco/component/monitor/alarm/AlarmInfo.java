package com.jd.jr.eco.component.monitor.alarm;


import com.jd.jr.eco.component.result.Result;

/**
 * 报警注册参数
 */
public interface AlarmInfo {

    /**
     * 设置返回参数
     * 只有在方法正常执行完又返回参数
     * 并且 {@code returnObj instance Result} is true
     * 才会调用该方法
     */
    void setResult(Result result);

    /**
     * 异常传递
     * 在执行方法抛出异常后进行拦截，并将异常进行设置
     * @param t
     */
    void setException(Throwable t);

    /**
     * 是否是异常情况
     * @return
     */
    boolean exception();





}
