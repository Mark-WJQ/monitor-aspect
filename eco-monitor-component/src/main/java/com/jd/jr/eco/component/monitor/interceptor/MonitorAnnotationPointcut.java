package com.jd.jr.eco.component.monitor.interceptor;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 监控注解切点
 * @author wangjianqiang24
 * @date 2020/6/3
 */
public class MonitorAnnotationPointcut extends StaticMethodMatcherPointcut {

    public MonitorAnnotationPointcut(Class<? extends Annotation> clazz) {
        this(clazz,new AnnotationCandidateClassFilter(clazz));
    }


    public MonitorAnnotationPointcut(Class<? extends Annotation> clazz,CandidateClassFilter filter) {
        this.annotationType = clazz;
        matcher = new AnnotationMethodMatcher(clazz,true);
        setClassFilter(filter);
    }

    /**
     * 注解类型
     */
    private Class<? extends Annotation> annotationType;

    public Class<? extends Annotation> getAnnotationType() {
        return annotationType;
    }

    /**
     * 方法匹配
     */
    private MethodMatcher matcher;

    /**
     * Perform static checking whether the given method matches.
     * <p>If this returns {@code false} or if the {@link #isRuntime()}
     * method returns {@code false}, no runtime check (i.e. no
     * {@link #matches(Method, Class, Object[])} call)
     * will be made.
     *
     * @param method      the candidate method
     * @param targetClass the target class
     * @return whether or not this method matches statically
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
       if (!Modifier.isPublic(method.getModifiers()) || Modifier.isStatic(method.getModifiers()) || Modifier.isFinal(method.getModifiers())){
           return false;
       }

        if (AnnotatedElementUtils.hasAnnotation(targetClass, this.annotationType)){
            return true;
        }

        return matcher.matches(method,targetClass);
    }


    private static class AnnotationCandidateClassFilter extends CandidateClassFilter {

        private final Class<? extends Annotation> annotationType;

        AnnotationCandidateClassFilter(Class<? extends Annotation> annotationType) {
            this.annotationType = annotationType;
        }

        @Override
        public boolean matches(Class<?> clazz) {


            return isCandidateClass(clazz, this.annotationType);
        }

        private boolean isCandidateClass(Class<?> clazz, Class<? extends Annotation> annotationType) {

            String annotationName = annotationType.getName();
            if (annotationName.startsWith("java.")) {
                return true;
            }
            if (hasPlainJavaAnnotationsOnly(clazz)) {
                return false;
            }
            return true;
        }

        private boolean hasPlainJavaAnnotationsOnly(Class<?> type) {
            return (type.getName().startsWith("java.") || type == Ordered.class);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MonitorAnnotationPointcut.AnnotationCandidateClassFilter)) {
                return false;
            }
            MonitorAnnotationPointcut.AnnotationCandidateClassFilter that = (MonitorAnnotationPointcut.AnnotationCandidateClassFilter) obj;
            return this.annotationType.equals(that.annotationType);
        }

        @Override
        public int hashCode() {
            return this.annotationType.hashCode();
        }

        @Override
        public String toString() {
            return getClass().getName() + ": " + this.annotationType;
        }

    }








}
