package com.wjq.component.monitor.starter.execute;


import com.wjq.component.monitor.meta.Monitor;
import com.wjq.component.result.Result;

import java.util.Collection;

/**
 * @author wangjianqiang24
 * @date 2020/6/2
 */

public interface TestAopInf<T extends Collection> {
     @Monitor
     Result monitor(T t);

}
