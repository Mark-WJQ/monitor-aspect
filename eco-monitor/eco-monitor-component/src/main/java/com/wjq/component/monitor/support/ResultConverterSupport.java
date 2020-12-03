package com.wjq.component.monitor.support;

import com.wjq.component.result.DefaultResult;
import com.wjq.component.result.Result;

/**
 * 结果转换支持接口
 *
 * @author wangjianqiang24
 * @date 2020/6/15
 */
public interface ResultConverterSupport<T> {


    /**
     * 旧结果转换成新结果
     * 可以转换成 {@link DefaultResult}
     *
     * @param t
     * @return
     */
    Result convert(T t);


}
