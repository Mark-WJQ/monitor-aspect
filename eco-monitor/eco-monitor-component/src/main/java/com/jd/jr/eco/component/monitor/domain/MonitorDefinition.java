package com.jd.jr.eco.component.monitor.domain;

import com.jd.jr.eco.component.monitor.meta.ProfEnum;
import com.jd.jr.eco.component.monitor.support.KeyGeneratorSupport;
import com.jd.jr.eco.component.monitor.support.ResultConverterSupport;

/**
 * @author wangjianqiang24
 * @date 2020/5/25
 */
public interface MonitorDefinition {

    /**
     * 获取应用名字
     *
     * @return
     */
    String getAppName();


    /**
     * 获取唯一标识
     *
     * @return
     */
    String getKey();


    /**
     * 报警异常
     *
     * @return
     */
    Class<? extends Throwable>[] getAlarms();

    /**
     * 异常记录
     *
     * @return
     */
    Class<? extends Throwable>[] getErrors();

    /**
     * 忽略异常
     *
     * @return
     */
    Class<? extends Throwable>[] getIngoreErrors();

    /**
     * 报警code
     *
     * @return
     */
    String[] getAlarmCodes();

    /**
     * 记录异常code
     *
     * @return
     */
    String[] getErrorCodes();

    /**
     * 忽略异常code
     *
     * @return
     */
    String[] getIngoreCodes();

    /**
     * 是否跳过
     * 如果设为 true 则该切面不做任何记录，将直接调用方法
     *
     * @return
     */
    boolean skip();

    /**
     * 监控类型
     *
     * @return
     */
    ProfEnum[] getProfEnums();


    /**
     * 获取唯一标识计算器
     *
     * @return
     */
    KeyGeneratorSupport getKeyGenerator();

    /**
     * 获取结果转换类型，如果方法执行返回结果类型不是 {@link com.jd.jr.eco.component.result.Result} 的子类，
     * 则需要将结果转换，以便能够在后续的流程中使用统一风格获取数据，减少分歧
     *
     * @return
     */
    ResultConverterSupport getResultConverter();


}
