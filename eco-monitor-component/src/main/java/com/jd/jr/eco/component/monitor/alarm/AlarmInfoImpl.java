package com.jd.jr.eco.component.monitor.alarm;


import com.jd.jr.eco.component.result.Result;

/**
 * @author wangjianqiang24
 * @date 2020/6/3
 */
public class AlarmInfoImpl implements AlarmInfo {


   private Result result;

   private Throwable t;

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
        this.t = t;
    }

    /**
     * 是否是异常情况
     *
     * @return
     */
    @Override
    public boolean exception() {
        return t == null;
    }


}
