package com.jd.jr.eco.component.monitor.alarm;


import com.jd.jr.eco.component.result.Result;

/**
 * @author wangjianqiang24
 * @date 2020/6/3
 */
public class AlarmInfoImpl implements AlarmInfo {


    private String key;

   private Result result;

   private Throwable t;

    public AlarmInfoImpl(String key) {
        this.key = key;
    }

    /**
     * 设置报警信息
     *
     * @param result
     */
    @Override
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * 异常传递
     *
     * @param t
     */
    @Override
    public void setException(Throwable t) {
        this.t = t;
    }

    /**
     * 是否是异常情况
     *
     * @return
     */
    @Override
    public boolean exception() {
        return t != null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Result getResult() {
        return result;
    }

    public Throwable getT() {
        return t;
    }

    public void setT(Throwable t) {
        this.t = t;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"key\":\"")
                .append(key).append('\"');
        sb.append(",\"result\":")
                .append(result);
        sb.append(",\"t\":")
                .append(t);
        sb.append("}");
        return sb.toString();
    }
}
