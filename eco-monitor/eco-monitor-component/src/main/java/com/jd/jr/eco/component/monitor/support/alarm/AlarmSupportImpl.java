package com.jd.jr.eco.component.monitor.support.alarm;

import com.jd.jr.eco.component.monitor.meta.DefaultMonitorAttribute;
import com.jd.jr.eco.component.monitor.support.SupportParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianqiang24
 * @date 2020/6/1
 */
public class AlarmSupportImpl extends AbstractAlarmSupport<AlarmInfoImpl, DefaultMonitorAttribute> {

    private static Logger logger = LoggerFactory.getLogger(AlarmSupportImpl.class);

    /**
     * 获取报警信息组成信息,
     * 有异常自己内部处理不要抛出来
     * 如果返回空则不记录信息
     *
     * @param param 方法请求参数
     * @param attribute 监控属性
     * @return
     */
    @Override
    public AlarmInfo registerInfo(SupportParam param, DefaultMonitorAttribute attribute) {
        logger.info("开始记录:{}",attribute.getKey());
        return new AlarmInfoImpl(attribute.getKey());
    }

    /**
     * 记录可用率
     *
     * @param info 可用率信息
     */
    @Override
    public void functionError(AlarmInfoImpl info) {


        if (info.exception()){

        }



        logger.error("大王遇到困难了，记录一下");
    }

    /**
     * 报警
     *
     * @param info 报警信息
     */
    @Override
    public void alarm(AlarmInfoImpl info) {
        logger.error("有问题了，需要报警");
    }

    /**
     * 结束记录
     *
     * @param info
     */
    @Override
    public void end(AlarmInfoImpl info) {
        logger.info("可以结束了");
    }

    /**
     * 忽略情况
     *
     * @param alarmInfo
     */
    @Override
    public void ingore(AlarmInfoImpl alarmInfo) {
        logger.warn("忽略不进行处理");

    }
}
