package com.jd.jr.eco.component.asserts;

import com.jd.jr.eco.component.enums.IResultEnum;
import com.jd.jr.eco.component.exception.CodeRunTimeException;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public interface CodeExceptionAssert extends Assert ,IResultEnum {

    @Override
    default CodeRunTimeException newException(Object... args) {
       String msg = this.getFormat().format(args);
        return new CodeRunTimeException(this,msg);
    }

    @Override
   default CodeRunTimeException newException(Throwable cause, Object... args){
        String msg = this.getFormat().format(getInfo(),args);
        return new CodeRunTimeException(this,msg,cause);
    }
}
