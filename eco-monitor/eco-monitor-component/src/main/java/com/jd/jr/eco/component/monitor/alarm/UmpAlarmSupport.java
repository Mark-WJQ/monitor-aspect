package com.jd.jr.eco.component.monitor.alarm;

import com.jd.jr.eco.component.monitor.domain.DefaultMonitorAttribute;
import com.jd.jr.eco.component.monitor.meta.ProfEnum;
import com.jd.jr.eco.component.monitor.meta.UmpConfig;
import com.jd.jr.eco.component.monitor.support.SupportParam;
import com.jd.ump.profiler.CallerInfo;
import com.jd.ump.profiler.proxy.Profiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * ump 监控辅助类
 *
 * @author wangjianqiang24
 * @date 2020/6/12
 */
public class UmpAlarmSupport extends AbstractAlarmSupport<UmpAlarmInfo, DefaultMonitorAttribute>{

    private static Logger logger = LoggerFactory.getLogger(UmpAlarmSupport.class);


    /**
     * ump 相关的一些配置信息
     */
    private UmpConfig umpConfig;

    public UmpAlarmSupport(UmpConfig umpConfig) {
        this.umpConfig = umpConfig;
    }

    /**
     * 获取报警信息组成信息,
     * 有异常自己内部处理不要抛出来
     * 如果返回空则不记录信息
     *
     * @param param
     * @param attribute
     * @return
     */
    @Override
    public AlarmInfo registerInfo(SupportParam param, DefaultMonitorAttribute attribute) {
        ProfEnum[] enums = attribute.getProfEnums();
        boolean heart = false;
        boolean tp = false;
        if (enums.length == 0) {
            tp = true;
        } else {
            for (ProfEnum e : enums) {
                switch (e) {
                    case TP:
                        tp = true;
                        break;
                    case Heartbeat:
                        heart = true;
                        break;
                    default:
                        break;
                }
            }
        }

        CallerInfo info = Profiler.registerInfo(attribute.getKey(), attribute.getAppName(), heart, tp);
        UmpAlarmInfo umpInfo = new UmpAlarmInfo(info);
        return umpInfo;
    }


    /**
     * 记录可用率
     *
     * @param info 可用率信息
     */
    @Override
    public void functionError(UmpAlarmInfo info) {
        if (logger.isDebugEnabled()) {
            logger.debug("ump functionError info,key:{}", info.getInfo().getKey());
        }
        Profiler.functionError(info.getInfo());
    }

    /**
     * 报警
     *
     * @param info 报警信息
     */
    @Override
    public void alarm(UmpAlarmInfo info) {
        CallerInfo callerInfo = info.getInfo();
        String msg = info.exception() ? info.getException().getMessage() : info.getResult().getInfo();
        if (logger.isDebugEnabled()) {
            if (info.exception()) {
                logger.error("ump alarm info,key:{}", info.getException(), callerInfo.getKey());
            } else {
                logger.debug("ump alarm info,key:{},msg:{}", callerInfo.getKey(), msg);
            }
        }
        Profiler.businessAlarm(callerInfo.getKey(), msg);
    }

    /**
     * 结束记录
     *
     * @param info
     */
    @Override
    public void end(UmpAlarmInfo info) {
        if (logger.isDebugEnabled()){
            logger.debug("ump monitor end,key:{}",info.getInfo().getKey());
        }
        Profiler.registerInfoEnd(info.getInfo());
    }

    /**
     * 忽略情况
     *
     * @param alarmInfo
     */
    @Override
    public void ingore(UmpAlarmInfo alarmInfo) {
        if (logger.isDebugEnabled()) {
            logger.debug("ingore ump monitor info,key:{},result:{}", alarmInfo.getInfo().getKey(), alarmInfo.exception() ? alarmInfo.getException() : alarmInfo.getResult().getCode());
        }
    }


    public void afterPropertiesSet() throws Exception {

        if (umpConfig == null) {
            return;
        }

        if (StringUtils.hasText(this.umpConfig.getSystemKey())) {
            Profiler.InitHeartBeats(this.umpConfig.getSystemKey());
        }

        if (StringUtils.hasText(this.umpConfig.getJvmKey())) {
            Profiler.registerJVMInfo(this.umpConfig.getJvmKey());
        }
    }
}
