package com.jd.jr.eco.component.monitor.support;


import com.jd.jr.eco.component.monitor.alarm.AlarmInfo;
import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;

import java.lang.reflect.Method;

public interface AlarmSupport<I extends AlarmInfo, A extends MonitorAttribute> {

    /**
     * 获取报警信息组成信息,
     * 有异常自己内部处理不要抛出来
     * 如果返回空则不记录信息
     *
     * @param method      当前执行方法
     * @param targetClass 该方法所属的类
     * @return
     */
    AlarmInfo registerInfo(Method method, Class targetClass, Object[] arguments, A attribute);

    /**
     * 记录方法调用情况,正常执行完成或异常执行后
     *
     * @param info
     */
    void record(I info, A attribute);

    /**
     * 结束记录
     *
     * @param info
     */
    void end(I info);


}
