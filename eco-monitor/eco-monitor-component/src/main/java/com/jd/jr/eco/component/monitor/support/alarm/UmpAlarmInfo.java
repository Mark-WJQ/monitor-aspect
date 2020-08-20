package com.jd.jr.eco.component.monitor.support.alarm;

import com.jd.jr.eco.component.result.Result;
import com.jd.ump.profiler.CallerInfo;

import java.util.Objects;

/**
 * ump 监控注册信息相关
 *
 * @author wangjianqiang24
 * @date 2020/6/12
 */
class UmpAlarmInfo implements AlarmInfo {

    public UmpAlarmInfo(CallerInfo info) {
        this.info = info;
    }

    /**
     * ump 记录信息
     */
    private CallerInfo info;

    /**
     * 执行结果
     */
    private Result result;

    /**
     * 执行异常
     */
    private Throwable exception;


    /**
     * 设置报警信息
     *
     * @param info
     */
    @Override
    public void setResult(Result info) {
        this.result = info;
    }

    /**
     * 异常传递
     *
     * @param t
     */
    @Override
    public void setException(Throwable t) {
        this.exception = t;
    }

    /**
     * 是否是异常情况
     *
     * @return
     */
    @Override
    public boolean exception() {
        return Objects.nonNull(exception);
    }

    public CallerInfo getInfo() {
        return info;
    }

    public void setInfo(CallerInfo info) {
        this.info = info;
    }

    public Result getResult() {
        return result;
    }

    public Throwable getException() {
        return exception;
    }

}
