package com.jd.jr.eco.component.monitor.interceptor;


import org.springframework.aop.ClassFilter;

/**
 * 潜在代理类过滤器，自定义过滤
 * 根据类名，包名等Class 的相关属性过滤出可能被代理的类
 * eg: 如果该服务是为业务使用的,那第三方的类就应该被过滤掉:org.springframework.aop.support.annotation
 * @see MonitorAnnotationPointcut.AnnotationCandidateClassFilter
 * @author wangjianqiang24
 * @date 2020/6/4
 */
public interface CandidateClassFilter extends ClassFilter {
}
