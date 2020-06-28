package com.jd.jr.eco.component.enums;

import com.jd.jr.eco.component.asserts.CodeExceptionAssert;

import java.text.MessageFormat;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public enum ReponseEnum implements CodeExceptionAssert {
    NULL("0001","{0} is null");


   private String code;
   private String info;
   private MessageFormat format;

    ReponseEnum(String code, String info) {
        this.code = code;
        this.info = info;
        this.format = new MessageFormat(info);
    }

    /**
     * 执行结果code
     *
     * @return
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 执行结果描述
     *
     * @return
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public MessageFormat getFormat() {
        return this.format;
    }
}
