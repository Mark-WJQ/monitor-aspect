package com.jd.jr.eco.component.monitor.domain;


import com.jd.jr.eco.component.monitor.annotation.Monitor;

import java.lang.annotation.Annotation;

/**
 * 监控配置类
 *
 * @author wangjianqiang24
 * @date 2020/5/29
 */
public class MonitorConfig {

    public static final String MONITOR_PRE = "monitor";
    /**
     * 表达式
     */
    private String expression;


    /**
     * 注解类,
     * 会对该注解所修饰的类做aop代理
     */
    private Class<? extends Annotation> annotation;


    /**
     * 应用名
     */
    private String appName;

    /**
     * 主键前缀，针对每个方法会有一个key
     * 在组成key是会用到前缀
     *
     * @see Monitor#key()
     */
    private String keyPre;


    /**
     * 报警异常
     */
    private Class<? extends Throwable>[] alarmExceptions;

    /**
     * 错误记录异常
     */
    private Class<? extends Throwable>[] errorExceptions;

    /**
     * 忽略异常
     */
    private Class<? extends Throwable>[] ingoreExceptions;

    /**
     * 错误code
     */
    private String[] errorCodes;

    /**
     * 报警code
     */
    private String[] alarmCodes;

    /**
     * 忽略code
     * 成功或幂等
     */
    private String[] ingoreCodes;

    /**
     * 监控类型
     */
    private ProfEnum[] profEnums = {ProfEnum.TP,ProfEnum.FunctionError};


    public ProfEnum[] getProfEnums() {
        return profEnums;
    }

    public void setProfEnums(ProfEnum[] profEnums) {
        this.profEnums = profEnums;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getKeyPre() {
        return keyPre;
    }

    public void setKeyPre(String keyPre) {
        this.keyPre = keyPre;
    }

    public Class<? extends Throwable>[] getAlarmExceptions() {
        return alarmExceptions;
    }

    public void setAlarmExceptions(Class<? extends Throwable>[] alarmExceptions) {
        this.alarmExceptions = alarmExceptions;
    }

    public Class<? extends Throwable>[] getErrorExceptions() {
        return errorExceptions;
    }

    public void setErrorExceptions(Class<? extends Throwable>[] errorExceptions) {
        this.errorExceptions = errorExceptions;
    }

    public Class<? extends Throwable>[] getIngoreExceptions() {
        return ingoreExceptions;
    }

    public void setIngoreExceptions(Class<? extends Throwable>[] ingoreExceptions) {
        this.ingoreExceptions = ingoreExceptions;
    }

    public String[] getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String[] errorCodes) {
        this.errorCodes = errorCodes;
    }

    public String[] getAlarmCodes() {
        return alarmCodes;
    }

    public void setAlarmCodes(String[] alarmCodes) {
        this.alarmCodes = alarmCodes;
    }

    public String[] getIngoreCodes() {
        return ingoreCodes;
    }

    public void setIngoreCodes(String[] ingoreCodes) {
        this.ingoreCodes = ingoreCodes;
    }

}
