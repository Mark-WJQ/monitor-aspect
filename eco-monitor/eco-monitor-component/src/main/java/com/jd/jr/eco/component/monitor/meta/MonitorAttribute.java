package com.jd.jr.eco.component.monitor.meta;

/**
 * 监控类型属性
 */
public interface MonitorAttribute extends MonitorDefinition {

    /**
     * 报警异常
     * @return
     */
   default boolean alarm(Throwable t){
       return false;
   }

    /**
     * 记录可用率异常,默认对全部异常记录
     * @return
     */
    default boolean error(Throwable t){
        return true;
    }


    /**
     * 忽略异常
     * 默认不忽略任何异常
     * @param t
     * @return
     */
   default boolean ingoreError(Throwable t){
        return false;
    }

    /**
     * 报警code
     * @return
     */
   default boolean alarmCode(String code){
       return false;
   }


    /**
     * 记录可用率code
     * 默认记录所有不被忽略的code，以免新增加code后不知道情况
     * @return
     */
   default boolean errorCode(String code){
       return true;
   }

    /**
     * 忽略code，认为是正常code
     * @param code
     * @return
     */
    boolean ingoreCode(String code);

    /**
     * 重新设置唯一标识
     * 主要用来在有el表达式或其他重新解析时重新设置
     * @param key
     */
    void resetKey(String key);

}
