package com.jd.jr.eco.component.monitor.alarm;


import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;

import java.lang.reflect.Method;

public interface AlarmSupport<I extends AlarmInfo,A extends MonitorAttribute> {

    /**
     * 获取报警信息组成信息,
     * 有异常自己内部处理不要抛出来
     * 如果返回空则不记录信息
     * @param method 当前执行方法
     * @param targetClass 该方法所属的类
     *
     * @return
     */
    AlarmInfo registerInfo(Method method, Class targetClass, Object[] arguments, A attribute);

    /**
     * 记录可用率
     * @param info 可用率信息
     */
    void functionError(I info);

    /**
     * 报警
     * @param info 报警信息
     */
    void alarm(I info);

    /**
     * 结束记录
     * @param info
     */
    void end(I info);

    /**
     * 忽略情况
     * @param alarmInfo
     */
    void ingore(I alarmInfo);

}
