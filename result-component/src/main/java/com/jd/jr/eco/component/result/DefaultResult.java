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
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":\"")
                .append(code).append('\"');
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append("}");
        return sb.toString();
    }
}
