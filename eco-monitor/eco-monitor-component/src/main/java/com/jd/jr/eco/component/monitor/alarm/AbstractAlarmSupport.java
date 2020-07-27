package com.jd.jr.eco.component.monitor.alarm;

import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;
import com.jd.jr.eco.component.monitor.support.AlarmSupport;
import com.jd.jr.eco.component.result.Result;

/**
 * @author wangjianqiang24
 * @date 2020/7/27
 */
public abstract class AbstractAlarmSupport<I extends AlarmInfo, A extends MonitorAttribute> implements AlarmSupport<I,A> {


    /**
     * 记录方法调用情况
     *
     * @param info
     * @param attribute
     */
    @Override
   public void record(I info, A attribute) {
        //异常
        if (info.exception()) {
            Throwable e = info.getException();
            if (attribute.ingoreError(e)) {
                //donothing or just log
                ingore(info);
            } else {
                if (attribute.error(e)) {
                    //记录可用率
                    functionError(info);
                }
                if (attribute.alarm(e)) {
                    //报警记录
                    alarm(info);
                }
            }
        } else {
            Result r = info.getResult();
            String code = r.getCode();
            if (attribute.ingoreCode(code)) {
                ingore(info);
            } else {
                if (attribute.errorCode(code)) {
                    // 记录可用率
                    functionError(info);
                }
                if (attribute.alarmCode(code)) {
                    // 报警记录
                    alarm(info);
                }
            }
        }

    }


    /**
     * 记录可用率
     *
     * @param info 可用率信息
     */
   protected abstract void functionError(I info);

    /**
     * 报警
     *
     * @param info 报警信息
     */
    protected abstract void alarm(I info);

    /**
     * 忽略情况
     *
     * @param alarmInfo
     */
    protected abstract void ingore(I alarmInfo);


}
