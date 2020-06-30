package com.jd.jr.eco.component.result;

/**
 * 执行结果的公共接口，可以以统一的格式拿到执行结果信息
 * @author wangjianqiang24
 */
public interface Result {

   /**
    * 执行结果code
    * @return
    */
   String getCode();

   /**
    * 执行结果描述
    * @return
    */
   String getInfo();

}
