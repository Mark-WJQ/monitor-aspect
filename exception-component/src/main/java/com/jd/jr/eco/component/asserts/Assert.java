package com.jd.jr.eco.component.asserts;

import java.text.MessageFormat;
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


    default void assertCond(Predicate cond,Object obj,Object... args){
        if (cond.test(obj)){
            throw newException(args);
        }
    }

    RuntimeException newException(Object... args);


    RuntimeException newException(Throwable cause,Object... args);

    MessageFormat getFormat();
}
