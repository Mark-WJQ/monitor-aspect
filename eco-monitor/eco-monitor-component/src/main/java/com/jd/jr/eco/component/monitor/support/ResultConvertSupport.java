package com.jd.jr.eco.component.monitor.support;

import com.jd.jr.eco.component.result.Result;

/**
 * 结果转换支持接口
 * @author wangjianqiang24
 * @date 2020/6/15
 */
public interface ResultConvertSupport<T> {


    /**
     * 旧结果转换成新结果
     * 可以转换成 {@link com.jd.jr.eco.component.result.DefaultResult}
     * @param t
     * @return
     */
    Result convert(T t);


}
