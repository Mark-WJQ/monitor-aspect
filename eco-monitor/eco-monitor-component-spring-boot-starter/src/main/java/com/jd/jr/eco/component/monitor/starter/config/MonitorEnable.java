package com.jd.jr.eco.component.monitor.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangjianqiang24
 * @date 2020/6/5
 */
@ConfigurationProperties(prefix = MonitorEnable.PRE)
public class MonitorEnable {

    public static final String PRE = "monitor.switch";

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
