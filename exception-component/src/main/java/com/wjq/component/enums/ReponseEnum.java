package com.wjq.component.enums;

import com.wjq.component.asserts.CodeExceptionAssert;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public enum ReponseEnum implements CodeExceptionAssert {
    NULL("0001","{0} is null");


   private String code;
   private String info;

    ReponseEnum(String code, String info) {
        this.code = code;
        this.info = info;
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

}
