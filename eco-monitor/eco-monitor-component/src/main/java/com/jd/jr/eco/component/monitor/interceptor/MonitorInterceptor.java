package com.jd.jr.eco.component.monitor.interceptor;


import com.jd.jr.eco.component.monitor.alarm.AlarmInfo;
import com.jd.jr.eco.component.monitor.alarm.AlarmSupport;
import com.jd.jr.eco.component.monitor.domain.MonitorAttribute;
import com.jd.jr.eco.component.monitor.domain.MonitorAttributeSource;
import com.jd.jr.eco.component.monitor.support.ResultConvertSupport;
import com.jd.jr.eco.component.result.Result;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

/**
 * 切面操作类，该类是对匹配到的对象做了代理处理，
 * 可以记录执行过程中出现的一些异常情况
 *
 * @author wangjianqiang24
 * @date 2020/06/02
 */
public class MonitorInterceptor implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(MonitorInterceptor.class);


    /**
     * 实际记录对象
     */
    private AlarmSupport alarmSupport;

    /**
     * 报警属性源
     */
    private MonitorAttributeSource attributeSource;

    /**
     * 结果转换辅助
     */
    private ResultConvertSupport resultConvertSupport;


    /**
     * 切面处理构造方法
     *
     * @param alarmSupport    处理方式
     * @param attributeSource 属性获取类
     */
    public MonitorInterceptor(AlarmSupport alarmSupport, MonitorAttributeSource attributeSource) {
        Assert.notNull(alarmSupport, "请设置 alarmSupport");
        Assert.notNull(attributeSource, "请设置 attributeSource");
        this.alarmSupport = alarmSupport;
        this.attributeSource = attributeSource;
    }


    public void setResultConvertSupport(ResultConvertSupport resultConvertSupport) {
        this.resultConvertSupport = resultConvertSupport;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MonitorAttribute attribute = getAttribute(invocation);
        if (Objects.isNull(attribute) || attribute.manual()) {
            return invocation.proceed();
        }
        AlarmInfo alarmInfo = registerInfo(invocation, attribute);
        if (Objects.isNull(alarmInfo)) {
            return invocation.proceed();
        }
        try {
            Object result = invocation.proceed();
            if (Objects.nonNull(result)) {
                Result r = getResult(result);
                if (Objects.nonNull(r)) {
                    handleCode(attribute, r, alarmInfo);
                }
            }
            return result;
        } catch (Throwable e) {
            handleError(attribute, e, alarmInfo);
            throw e;
        } finally {
            end(alarmInfo);
        }
    }

    private void end(AlarmInfo alarmInfo) {
        try {
            alarmSupport.end(alarmInfo);
        } catch (Throwable t) {
            logger.error("监控结束异常，请注意：{}", t);
        }
    }

    private AlarmInfo registerInfo(MethodInvocation invocation, MonitorAttribute attribute) {
        AlarmInfo alarmInfo = null;
        try {
            alarmInfo = alarmSupport.registerInfo(invocation.getMethod(), AopUtils.getTargetClass(invocation.getThis()), invocation.getArguments(), attribute);
        } catch (Throwable t) {
            logger.error("注册监控信息异常", t);
        }
        return alarmInfo;
    }


    private MonitorAttribute getAttribute(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (!Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()) || Modifier.isFinal(method.getModifiers())) {
            return null;
        }
        MonitorAttribute attribute = null;
        try {
            attribute = attributeSource.getMonitorAttribute(method, AopUtils.getTargetClass(invocation.getThis()));
        } catch (Throwable t) {
            logger.error("获取监控属性时异常", t);
        }
        return attribute;
    }

    /**
     * 处理异常
     *
     * @param attribute 属性
     * @param e         异常
     * @param alarmInfo 注册报警信息
     */
    private void handleError(MonitorAttribute attribute, Throwable e, AlarmInfo alarmInfo) {
        try {
            alarmInfo.setException(e);
            if (attribute.ingoreError(e)) {
                //donothing or just log
                alarmSupport.ingore(alarmInfo);
            } else {
                if (attribute.error(e)) {
                    //记录可用率
                    alarmSupport.functionError(alarmInfo);
                }
                if (attribute.alarm(e)) {
                    //报警记录
                    alarmSupport.alarm(alarmInfo);
                }
            }
        } catch (Throwable t) {
            logger.error("监控处理异常请注意", t);
        }
    }

    /**
     * 处理code
     *
     * @param attribute
     * @param result
     * @param alarmInfo
     */
    private void handleCode(MonitorAttribute attribute, Result result, AlarmInfo alarmInfo) {
        try {
            alarmInfo.setResult(result);
            String code = result.getCode();
            if (StringUtils.isEmpty(code)) {
                return;
            }
            if (attribute.ingoreCode(code)) {
                alarmSupport.ingore(alarmInfo);
            } else {
                if (attribute.errorCode(code)) {
                    // 记录可用率
                    alarmSupport.functionError(alarmInfo);
                }
                if (attribute.alarmCode(code)) {
                    // 报警记录
                    alarmSupport.alarm(alarmInfo);
                }
            }
        } catch (Throwable t) {
            //忽略以免影响正常流程，或是日志记录
            logger.error("监控处理异常请注意", t);
        }
    }


    private Result getResult(Object result) {
        try {
            if (result instanceof Result) {
                return (Result) result;
            } else {
                if (Objects.nonNull(resultConvertSupport)) {
                    return resultConvertSupport.convert(result);
                }
            }
        } catch (Throwable t) {
            logger.error("监控结果转换异常", t);
        }
        return null;
    }


}
