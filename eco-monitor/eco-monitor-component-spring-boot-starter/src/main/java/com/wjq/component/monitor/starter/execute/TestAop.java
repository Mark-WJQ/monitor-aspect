package com.wjq.component.monitor.starter.execute;


import com.wjq.component.monitor.meta.Monitor;
import com.wjq.component.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author wangjianqiang24
 * @date 2020/6/2
 */
@Component
@Monitor
public class TestAop implements TestAopInf<List> {


    @Override
    @Validated
    @Monitor
    public Result monitor(List list){
        System.out.println("--------------------------------");
        return new Result() {
            @Override
            public String getCode() {
                return "0000";
            }

            @Override
            public String getInfo() {
                return "成功";
            }
        };
    }

    public void t1(){
        System.out.println("t1-----------------");
        int i = 1/0;
    }



}
