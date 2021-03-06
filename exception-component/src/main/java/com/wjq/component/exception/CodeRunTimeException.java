package com.wjq.component.exception;

import com.wjq.component.enums.IResultEnum;
import com.wjq.component.result.Result;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public class CodeRunTimeException extends RuntimeException implements Result {

    /**
     * 状态码
     */
    private String code;

    /**
     * 错误信息
     */
    private String info;


    public CodeRunTimeException(IResultEnum resultEnum, String message, Throwable cause) {
        super(message, cause);
        this.code = resultEnum.getCode();
        this.info = message;
    }


    public CodeRunTimeException(IResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
        this.info = message;
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
