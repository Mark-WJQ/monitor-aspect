package com.jd.jr.eco.component.monitor.interceptor;


import com.jd.jr.eco.component.monitor.support.alarm.AlarmInfo;
import com.jd.jr.eco.component.monitor.meta.MonitorAttribute;
import com.jd.jr.eco.component.monitor.meta.MonitorAttributeSource;
import com.jd.jr.eco.component.monitor.support.alarm.AlarmSupport;
import com.jd.jr.eco.component.monitor.support.SupportParam;
import com.jd.jr.eco.component.monitor.support.ResultConverterSupport;
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

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MonitorAttribute attribute = getAttribute(invocation);
        if (Objects.isNull(attribute) || attribute.skip()) {
            return invocation.proceed();
        }
        AlarmInfo alarmInfo = registerInfo(invocation, attribute);
        if (Objects.isNull(alarmInfo)) {
            return invocation.proceed();
        }
        try {
            Object result = invocation.proceed();
            if (Objects.nonNull(result)) {
                Result r = getResult(result, attribute.getResultConverter());
                if (Objects.nonNull(r)) {
                    recordCode(attribute, r, alarmInfo);
                }
            }
            return result;
        } catch (Throwable e) {
            recordError(attribute, e, alarmInfo);
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
            SupportParam param = new SupportParam(invocation.getMethod(),AopUtils.getTargetClass(invocation.getThis()),invocation.getArguments());
            resetKey(param, attribute);
            alarmInfo = alarmSupport.registerInfo(param, attribute);
        } catch (Throwable t) {
            logger.error("注册监控信息异常", t);
        }
        return alarmInfo;
    }

    private void resetKey(SupportParam param, MonitorAttribute attribute) {
        if (Objects.nonNull(attribute.getKeyGenerator())) {
            String newKey = attribute.getKeyGenerator().calculate(param, attribute);
            if (StringUtils.isEmpty(newKey)) {
                logger.warn("the method:{} calculate new key is null,oldkey is:{}", param.getMethod().getName(), attribute.getKey());
                return;
            }
            attribute.resetKey(newKey);
        }
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
    private void recordError(MonitorAttribute attribute, Throwable e, AlarmInfo alarmInfo) {
        try {
            alarmInfo.setException(e);
            alarmSupport.record(alarmInfo, attribute);
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
    private void recordCode(MonitorAttribute attribute, Result result, AlarmInfo alarmInfo) {
        try {
            alarmInfo.setResult(result);
            String code = result.getCode();
            if (StringUtils.isEmpty(code)) {
                if (logger.isDebugEnabled()) {
                    logger.debug("key:{},未转换出code,直接返回不做记录", attribute.getKey());
                }
                return;
            }
            alarmSupport.record(alarmInfo, attribute);
        } catch (Throwable t) {
            //忽略以免影响正常流程，或是日志记录
            logger.error("监控处理异常请注意", t);
        }
    }


    private Result getResult(Object result, ResultConverterSupport resultConverter) {
        try {
            if (result instanceof Result) {
                return (Result) result;
            } else {
                if (Objects.nonNull(resultConverter)) {
                    return resultConverter.convert(result);
                }
            }
        } catch (Throwable t) {
            logger.error("监控结果转换异常", t);
        }
        return null;
    }


}
