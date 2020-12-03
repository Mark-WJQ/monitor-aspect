package com.wjq.component.asserts;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public interface Assert {

    default void assertNotNull(Object obj,Object... args){
        assertCond((o)-> Objects.isNull(o),obj,args);
    }



    default <T> void assertCond(Predicate<T> cond, T obj, Object... args){
        if (cond.test(obj)){
            throw newException(args);
        }
    }

    /**
     * 直接指定原因
     * @param cond
     * @param obj
     * @param msg
     */
    default <T> void assertCond(Predicate<T> cond, T obj, String msg){
        if (cond.test(obj)){
            throw newException(msg);
        }
    }

    RuntimeException newException(Object... args);


    RuntimeException newException(Throwable cause,Object... args);

}
