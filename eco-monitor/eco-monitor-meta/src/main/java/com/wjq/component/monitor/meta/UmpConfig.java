package com.wjq.component.monitor.meta;

/**
 * @author wangjianqiang24
 * @date 2020/6/12
 */
public class UmpConfig {

    public static final String UMP_CONFIG_PRE = "ump.monitor";

    private boolean enable;

    private String systemKey;

    private String jvmKey;

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getJvmKey() {
        return jvmKey;
    }

    public void setJvmKey(String jvmKey) {
        this.jvmKey = jvmKey;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"enable\":")
                .append(enable);
        sb.append(",\"systemKey\":\"")
                .append(systemKey).append('\"');
        sb.append(",\"jvmKey\":\"")
                .append(jvmKey).append('\"');
        sb.append("}");
        return sb.toString();
    }
}
