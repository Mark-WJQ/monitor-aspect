package com.jd.jr.eco.component.monitor.starter.execute;


import com.jd.jr.eco.component.monitor.meta.Monitor;
import com.jd.jr.eco.component.result.Result;

import java.util.Collection;

/**
 * @author wangjianqiang24
 * @date 2020/6/2
 */

public interface TestAopInf<T extends Collection> {
     @Monitor
     Result monitor(T t);

}
