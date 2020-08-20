package com.jd.jr.eco.component.monitor.starter.config;


import com.jd.jr.eco.component.monitor.alarm.AlarmSupportImpl;
import com.jd.jr.eco.component.monitor.alarm.UmpAlarmSupport;
import com.jd.jr.eco.component.monitor.meta.AnnotationMonitorAttributeSource;
import com.jd.jr.eco.component.monitor.meta.CachedAttributeSource;
import com.jd.jr.eco.component.monitor.meta.CompositeMonitorAttributeSource;
import com.jd.jr.eco.component.monitor.meta.ConfigMonitorAttributeSource;
import com.jd.jr.eco.component.monitor.meta.MonitorAttributeSource;
import com.jd.jr.eco.component.monitor.interceptor.CandidateClassFilter;
import com.jd.jr.eco.component.monitor.interceptor.MonitorAnnotationPointcut;
import com.jd.jr.eco.component.monitor.interceptor.MonitorInterceptor;
import com.jd.jr.eco.component.monitor.meta.DefaultMonitorAnnotationParser;
import com.jd.jr.eco.component.monitor.meta.Monitor;
import com.jd.jr.eco.component.monitor.meta.MonitorAnnotationParser;
import com.jd.jr.eco.component.monitor.meta.MonitorConfig;
import com.jd.jr.eco.component.monitor.meta.UmpConfig;
import com.jd.jr.eco.component.monitor.starter.adapter.MonitorPointcutAdapter;
import com.jd.jr.eco.component.monitor.starter.execute.Inject;
import com.jd.jr.eco.component.monitor.support.AlarmSupport;
import com.jd.jr.eco.component.monitor.support.AttributeSourceSupport;
import com.jd.jr.eco.component.monitor.support.DefaultKeyGeneratorSupport;
import com.jd.jr.eco.component.monitor.support.KeyGeneratorSupport;
import com.jd.jr.eco.component.monitor.support.SpringELKeyGeneratorSupport;
import org.aopalliance.aop.Advice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import static com.jd.jr.eco.component.monitor.meta.UmpConfig.UMP_CONFIG_PRE;

/**
 * 初始化 configuration
 *
 * @author wangjianqiang24
 * @date 2020/5/29
 */
@Configuration
@EnableConfigurationProperties(MonitorEnable.class)
@ConditionalOnProperty(prefix = MonitorEnable.PRE, name = "enable", havingValue = "true")
public class MonitorConfiguration {


    @Bean
    @ConfigurationProperties(prefix = UMP_CONFIG_PRE)
    @ConditionalOnProperty(prefix = UMP_CONFIG_PRE, name = "enable", havingValue = "true")
    @ConditionalOnClass(name = {"com.jd.ump.profiler.proxy.Profiler"})
    public UmpConfig umpConfig() {
        return new UmpConfig();
    }


    @Bean(initMethod = "afterPropertiesSet")
    @ConditionalOnProperty(prefix = UMP_CONFIG_PRE, name = "enable", havingValue = "true")
    @ConditionalOnClass(name = {"com.jd.ump.profiler.proxy.Profiler"})
    public UmpAlarmSupport umpAlarmSupport() {
        return new UmpAlarmSupport(umpConfig());
    }


    @Bean
    @ConfigurationProperties(prefix = MonitorConfig.MONITOR_PRE)
    @ConditionalOnMissingBean
    public MonitorConfig monitorConfig() {
        return new MonitorConfig();
    }


    @Bean
    @ConditionalOnBean(name = "monitorConfig", value = MonitorConfig.class)
    @ConditionalOnProperty(prefix = MonitorConfig.MONITOR_PRE, name = "expression")
    public MonitorPointcutAdapter expressionPointcut(MonitorConfig monitorConfig) {
        AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
        pointCut.setExpression(monitorConfig.getExpression());
        return new MonitorPointcutAdapter(pointCut);
    }

    @Bean
    @ConditionalOnBean(name = "monitorConfig", value = MonitorConfig.class)
    @ConditionalOnProperty(prefix = MonitorConfig.MONITOR_PRE, name = "annotation")
    public MonitorPointcutAdapter annotationMatchingPointcut(MonitorConfig monitorConfig, ObjectProvider<CandidateClassFilter> classFilter) {
        if (classFilter.getIfAvailable() != null) {
            Pointcut pointcut = new MonitorAnnotationPointcut(monitorConfig.getAnnotation(), classFilter.getIfAvailable());
            return new MonitorPointcutAdapter(pointcut);
        }
        return getAnnoAdapter(monitorConfig.getAnnotation());
    }

    @Bean
    @ConditionalOnMissingBean
    public MonitorPointcutAdapter defaultPointcutAdapter() {
        return getAnnoAdapter(Monitor.class);
    }

    /**
     * 获取注解配置
     *
     * @param clazz
     * @return
     */
    private MonitorPointcutAdapter getAnnoAdapter(Class<? extends Annotation> clazz) {
        Pointcut pointcut = new MonitorAnnotationPointcut(clazz);
        return new MonitorPointcutAdapter(pointcut);
    }


    @Bean
    public Pointcut composablePointcut(ObjectProvider<MonitorPointcutAdapter[]> provider) {
        MonitorPointcutAdapter[] monitorPointcutAdapters = provider.getIfAvailable();
        if (monitorPointcutAdapters.length > 1) {
            ComposablePointcut composablePointcut = new ComposablePointcut(new FALSEPointcut());
            for (MonitorPointcutAdapter adapter : monitorPointcutAdapters) {
                composablePointcut.union(adapter.getPointcut());
            }
            return composablePointcut;
        }
        return monitorPointcutAdapters[0].getPointcut();
    }


    @Bean
    @ConditionalOnMissingBean
    public AlarmSupport alarmSupport() {
        return new AlarmSupportImpl();
    }


    @Bean
    @ConditionalOnMissingBean
    public MonitorAnnotationParser annotationParser() {
        return new DefaultMonitorAnnotationParser();
    }


    @Bean
    @ConditionalOnMissingBean
    public MonitorAttributeSource attributeSource(MonitorConfig monitorConfig, List<MonitorAnnotationParser> annotationParsers,AttributeSourceSupport attributeSourceSupport) {
        AnnotationMonitorAttributeSource annotationMonitorAttributeSource = new AnnotationMonitorAttributeSource(annotationParsers);
        ConfigMonitorAttributeSource configMonitorAttributeSource = new ConfigMonitorAttributeSource(monitorConfig,attributeSourceSupport);
        CompositeMonitorAttributeSource source = new CompositeMonitorAttributeSource(annotationMonitorAttributeSource, configMonitorAttributeSource);
        return new CachedAttributeSource(source);
    }


    @Bean
    public Advice monitorAdvice(AlarmSupport alarmSupport, MonitorAttributeSource attributeSource) {
        MonitorInterceptor interceptor = new MonitorInterceptor(alarmSupport, attributeSource);
        return interceptor;
    }


    @Bean
    public DefaultPointcutAdvisor monitorAdvisor(Pointcut composablePointcut, Advice monitorAdvice) {
        return new DefaultPointcutAdvisor(composablePointcut, monitorAdvice);
    }


    @Bean
    public KeyGeneratorSupport springELKeyGenerator() {
        return new SpringELKeyGeneratorSupport();
    }

    @Bean
    public DefaultKeyGeneratorSupport defaultKeyGenerator(MonitorConfig monitorConfig){
        return new DefaultKeyGeneratorSupport(monitorConfig);
    }


    @Bean
    public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessorInject(){
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(Inject.class);
        return processor;
    }

    @Bean
    public AttributeSourceSupport attributeSourceSupport() {
        return new AttributeSourceSupport();
    }

    private class FALSEPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return clazz -> false;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new StaticMethodMatcher() {
                @Override
                public boolean matches(Method method, Class<?> targetClass) {
                    return false;
                }
            };
        }
    }

}
