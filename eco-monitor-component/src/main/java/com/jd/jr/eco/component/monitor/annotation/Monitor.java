package com.jd.jr.eco.component.monitor.annotation;


import com.jd.jr.eco.component.monitor.domain.MonitorConfig;
import com.jd.jr.eco.component.monitor.domain.ProfEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 监控注解信息，用来配置一些监控
 * 该注解可使用在类上，表示该注解属性对整个类的方法都有效
 * 如果该注解在类上跟方法上都存在，以方法属性为准，并不会合并
 * @author wangjianqiang24
 * @date 2020/06/01
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Monitor {


    @AliasFor("key")
    String value() default "";

    /**
     * 唯一标识,建议在使用时主动指定
     * <p>
     * 如果key是从类上的注解中取得并且不为空，则认为该key为每个方法key的前缀，在进行组装时会 {@code key + "." + method.name()}
     * 如果key是从方法注解上取得并且不为空，则可以直接使用
     * 如果该注解中的key为空
     * 1. 如果 {@link MonitorConfig#getKeyPre()} 不为空,则 {@code key = String.join(".", monitorConfig.getKeyPre(), method.getDeclaringClass().getSimpleName(), method.getName())}
     * 2. 否则 {@code key = String.join(".", method.getDeclaringClass().getName(), method.getName())}
     *
     * @return
     */
    @AliasFor("value")
    String key() default "";

    /**
     * 报警异常
     * 配置覆盖规则参考 {@link Monitor#errors()}
     *
     * @return
     * @see MonitorConfig#getAlarmExceptions()
     */
    Class<? extends Throwable>[] alarms() default {};

    /**
     * 记录可用率异常,
     * 如果该配置不为空则直接使用该配置信息过滤；
     * 如果该配置为空，查看 {@link Monitor#mergeConfig()}
     * 1. {@link MonitorConfig#getErrorExceptions()} 也为空，则默认对所有异常记录
     * 2. {@link MonitorConfig#getErrorExceptions()} 不为空，则使用 {@code MonitorConfig} 中配置
     *
     * @return
     */
    Class<? extends Throwable>[] errors() default {};


    /**
     * 忽略记录,配置覆盖规则参考 {@link Monitor#errors()}
     *
     * @return
     * @see MonitorConfig#getIngoreExceptions()
     */
    Class<? extends Throwable>[] ingoreErrors() default {};


    /**
     * 报警code，配置覆盖规则参考 {@link Monitor#errors()}
     *
     * @return
     * @see MonitorConfig#getAlarmCodes()
     */
    String[] alarmCodes() default {};


    /**
     * 记录可用率code，配置覆盖规则参考 {@link Monitor#errors()}
     *
     * @return
     * @see MonitorConfig#getErrorCodes()
     */
    String[] errorCodes() default {};


    /**
     * 忽略code，配置覆盖规则参考 {@link Monitor#errors()}
     *
     * @return
     * @see MonitorConfig#getIngoreCodes()
     */
    String[] ingoreCodes() default {};

    /**
     * 是否与公共配置的配置进行合并
     * 如果注解中的值为空列表或空字符串且公共配置部位空{@code str == null || str.equals("")}
     * 前提是 {@link MonitorConfig} 中的配置不为空
     * 配置为 {@code true} 进行合并
     * false 跳过不处理
     *
     * @return
     */
    boolean mergeConfig() default true;


    /**
     * 是否手动监控
     * 如果设为 true 则该切面不做任何记录，将直接调用方法
     *
     * @return
     */
    boolean manual() default false;

    /**
     * 监控类型
     * @return
     */
    ProfEnum[] profEnums() default {ProfEnum.FunctionError,ProfEnum.TP};


}
