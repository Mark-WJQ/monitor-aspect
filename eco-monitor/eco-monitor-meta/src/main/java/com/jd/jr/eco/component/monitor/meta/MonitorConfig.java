package com.jd.jr.eco.component.monitor.meta;


import java.lang.annotation.Annotation;
import java.util.Arrays;

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
     * 应用名,主键前缀，针对每个方法会有一个key
     */
    private String appName;


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

    /**
     * 主键生成
     */
    private String keyGenerator;


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

    public String getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(String keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"expression\":\"")
                .append(expression).append('\"');
        sb.append(",\"annotation\":")
                .append(annotation);
        sb.append(",\"appName\":\"")
                .append(appName).append('\"');
        sb.append(",\"alarmExceptions\":")
                .append(Arrays.toString(alarmExceptions));
        sb.append(",\"errorExceptions\":")
                .append(Arrays.toString(errorExceptions));
        sb.append(",\"ingoreExceptions\":")
                .append(Arrays.toString(ingoreExceptions));
        sb.append(",\"errorCodes\":")
                .append(Arrays.toString(errorCodes));
        sb.append(",\"alarmCodes\":")
                .append(Arrays.toString(alarmCodes));
        sb.append(",\"ingoreCodes\":")
                .append(Arrays.toString(ingoreCodes));
        sb.append(",\"profEnums\":")
                .append(Arrays.toString(profEnums));
        sb.append(",\"keyGenerator\":\"")
                .append(keyGenerator).append('\"');
        sb.append("}");
        return sb.toString();
    }
}
