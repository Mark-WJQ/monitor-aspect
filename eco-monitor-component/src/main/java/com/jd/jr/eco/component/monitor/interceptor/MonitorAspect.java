package com.jd.jr.eco.component.monitor.interceptor;

import com.jd.jr.eco.component.result.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangjianqiang24
 * @date 2020/6/8
 */
@Aspect
public class MonitorAspect {


    private static Logger logger = LoggerFactory.getLogger(MonitorAspect.class);

    @Pointcut("@annotation(com.wjq.monitor.annotation.Monitor)")
    public void pointcut(){

    }


    @AfterReturning(value = "pointcut()",returning = "ret")
    public void doAfter(JoinPoint point,Object ret){
        logger.info("invoke after:{}",((Result)ret).getCode());
    }


    @AfterThrowing(value = "pointcut()",throwing = "t")
    public void doAfterThrow(JoinPoint point, Throwable t){


    }


    @Before("pointcut()")
    public void doBefore(){

    }
/*

    @Around("pointcut()")
    public void doAround(){

    }
*/

}
