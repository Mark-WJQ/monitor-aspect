package com.jd.jr.eco.component.asserts;

import java.util.Objects;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public interface Assert {

    default void assertNotNull(Object obj,Object... args){
        if (Objects.isNull(obj)){
            throw newException(args);
        }
    }

    RuntimeException newException(Object... args);


    RuntimeException newException(Throwable cause,Object... args);

}
