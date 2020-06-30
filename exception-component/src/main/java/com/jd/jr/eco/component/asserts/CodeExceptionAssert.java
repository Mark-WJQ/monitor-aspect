package com.jd.jr.eco.component.asserts;

import com.jd.jr.eco.component.enums.IResultEnum;
import com.jd.jr.eco.component.exception.CodeRunTimeException;

import java.text.MessageFormat;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public interface CodeExceptionAssert extends Assert, IResultEnum {

    @Override
    default CodeRunTimeException newException(Object... args) {
        String msg = MessageFormat.format(this.getInfo(), args);
        return new CodeRunTimeException(this, msg);
    }

    @Override
    default CodeRunTimeException newException(Throwable cause, Object... args) {
        String msg = MessageFormat.format(this.getInfo(), args);
        return new CodeRunTimeException(this, msg, cause);
    }
}
