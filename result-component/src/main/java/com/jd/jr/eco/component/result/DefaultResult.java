package com.jd.jr.eco.component.result;

/**
 * 用来转换原有的执行结果
 * @author wangjianqiang24
 * @date 2020/6/15
 */
public class DefaultResult<T> implements Result {

    /**
     * 状态码
     */
    private String code;

    /**
     * 状态信息
     */
    private String info;

    /**
     * 真实执行结果
     */
    private T realResult;

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

    public void setCode(String code) {
        this.code = code;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setRealResult(T realResult) {
        this.realResult = realResult;
    }

    public T getRealResult() {
        return realResult;
    }
}
