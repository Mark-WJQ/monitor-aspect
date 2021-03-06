package com.wjq.component.monitor.meta;

import java.lang.annotation.*;

/**
 * 监控注解信息，用来配置一些监控
 * 该注解可使用在类上，表示该注解属性对整个类的方法都有效
 * 如果该注解在类上跟方法上都存在，以方法属性为准，并不会合并
 *
 * @author wangjianqiang24
 * @date 2020/06/01
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Monitor {


    /**
     * 唯一标识,建议在使用时主动指定
     * <p>
     * 该key可以使用占位符，可以自定义，配合 {@link Monitor#keyGenerator()}使用更健康
     * 可以参考实现
     */
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
     * 如果注解中的值为空列表或空字符串且公共配置不为空{@code str == null || str.equals("")}
     * 会对这些配置进行合并
     * {@link Monitor#ingoreCodes()},{@link Monitor#errorCodes()},
     * {@link Monitor#alarmCodes()},{@link Monitor#ingoreErrors()},
     * {@link Monitor#errors()},{@link Monitor#alarms()}
     * <p>
     * 前提是 {@link MonitorConfig} 中的配置不为空
     * 配置为 {@code true} 进行合并
     * false 跳过不处理
     *
     * @return
     */
    boolean mergeConfig() default true;


    /**
     * 是否跳过对该方法的监控，通常用于需要手动添加监控时，避免重复监控
     * 如果设为 true 则该切面不做任何记录，将直接调用方法
     *
     * @return
     */
    boolean skip() default false;

    /**
     * 监控类型
     *
     * @return
     */
    ProfEnum[] profEnums() default {ProfEnum.FunctionError, ProfEnum.TP};


    /**
     * {@code KeyGeneratorSupport} 通过实现该接口来实现生成 {@link Monitor#key()} 的规则
     * <p>
     * 通过该配置可以在上下文中找到 {@code KeyGeneratorSupport} 实现
     *
     * @see com.wjq.component.monitor.support.SpringELKeyGeneratorSupport
     * eg: ${app.name}.className.methodName, app.name 是占位符标识，会在上下文配置中查找对应的配置，如果找不到则原样返回
     */
    String keyGenerator() default "defaultKeyGenerator";

    /**
     * 方法结果执行转换器,如果方法执行返回结果类型不是 {@link com.wjq.component.result.Result} 的子类，
     * 则需要将结果转换，以便能够在后续的流程中使用统一风格获取数据，减少分歧
     *
     * @return
     */
    String resultConverter() default "";


}
