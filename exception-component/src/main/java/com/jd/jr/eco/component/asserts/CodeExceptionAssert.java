package com.jd.jr.eco.component.asserts;

import com.jd.jr.eco.component.enums.IResultEnum;
import com.jd.jr.eco.component.exception.CodeException;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public interface CodeExceptionAssert extends Assert ,IResultEnum {

    @Override
    default CodeException newException(Object... args) {
       String msg = this.getFormat().format(args);
        return new CodeException(this,msg);
    }

    @Override
   default CodeException newException(Throwable cause, Object... args){
        String msg = this.getFormat().format(getInfo(),args);
        return new CodeException(this,msg,cause);
    }
}
