package com.jd.jr.eco.component.monitor.domain;


import com.jd.jr.eco.component.monitor.meta.ProfEnum;
import com.jd.jr.eco.component.monitor.support.KeyCalculater;

/**
 * @author wangjianqiang24
 * @date 2020/5/29
 */
public class DefaultMonitorDefinition implements MonitorDefinition {

    /**
     * 记录主键
     */
   private String key;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 报警异常
     *
     * @return
     */
    private Class<? extends Throwable>[] alarms;

    /**
     * 记录可用率异常
     *
     * @return
     */
    private Class<? extends Throwable>[] errors;


    /**
     * 忽略记录
     *
     * @return
     */
    private Class<? extends Throwable>[] ingoreErrors;


    /**
     * 报警code,
     * code也可以自定义表达式，匹配规则需要自己扩展
     * {@link MonitorAttribute#alarmCode(String)}
     *
     * @return
     */
    private String[] alarmCodes;


    /**
     * 记录可用率code
     * code也可以自定义表达式，匹配规则需要自己扩展
     * {@link MonitorAttribute#errorCode(String)} (String)}
     * @return
     */
    private String[] errorCodes;


    /**
     * 忽略code
     * code也可以自定义表达式，匹配规则需要自己扩展
     * {@link MonitorAttribute#ingoreCode(String)} (String)} (String)}
     * 
     * @return
     */
    private String[] ingoreCodes;

    /**
     * 是否进行手动监控
     * 如果设置为true，则该方法不纳入自动监控的方位，需要开发自己在方法里增加监控
     */
    private boolean skip;

    /**
     * 监控指标类型
     */
    private ProfEnum[] profEnums;

    /**
     * 对monitorKey进行计算
     */
    private KeyCalculater keyCalculater;


    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    @Override
    public Class<? extends Throwable>[] getAlarms() {
        return alarms;
    }

    public void setAlarms(Class<? extends Throwable>[] alarms) {
        this.alarms = alarms;
    }

    @Override
    public Class<? extends Throwable>[] getErrors() {
        return errors;
    }

    public void setErrors(Class<? extends Throwable>[] errors) {
        this.errors = errors;
    }

    @Override
    public Class<? extends Throwable>[] getIngoreErrors() {
        return ingoreErrors;
    }

    public void setIngoreErrors(Class<? extends Throwable>[] ingoreErrors) {
        this.ingoreErrors = ingoreErrors;
    }

    @Override
    public String[] getAlarmCodes() {
        return alarmCodes;
    }

    public void setAlarmCodes(String[] alarmCodes) {
        this.alarmCodes = alarmCodes;
    }

    @Override
    public String[] getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String[] errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Override
    public String[] getIngoreCodes() {
        return ingoreCodes;
    }

    /**
     * 是否手动监控
     * 如果设为 true 则该切面不做任何记录，将直接调用方法
     *
     * @return
     */
    @Override
    public boolean skip() {
        return this.skip;
    }

    /**
     * 监控类型
     *
     * @return
     */
    @Override
    public ProfEnum[] getProfEnums() {
        return this.profEnums;
    }


    /**
     * 获取唯一标识计算器
     *
     * @return
     */
    @Override
    public KeyCalculater getKeyCalculater() {
        return this.keyCalculater;
    }

    public void setIngoreCodes(String[] ingoreCodes) {
        this.ingoreCodes = ingoreCodes;
    }

    /**
     * 获取应用名字
     *
     * @return
     */
    @Override
    public String getAppName() {
        return this.appName;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void setProfEnums(ProfEnum[] profEnums) {
        this.profEnums = profEnums;
    }

    public void setKeyCalculater(KeyCalculater keyCalculater) {
        this.keyCalculater = keyCalculater;
    }

}
