package com.jd.jr.eco.component.monitor.support;

import com.jd.jr.eco.component.monitor.domain.DefaultMonitorDefinition;
import com.jd.jr.eco.component.monitor.starter.execute.TestAop;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
class SpringELKeyCalculaterTest {


    private SpringELKeyGeneratorSupport springELKeyCalculater = new SpringELKeyGeneratorSupport();

    @Test
    void calculate() throws NoSuchMethodException {
        Method method = TestAop.class.getMethod("monitor", List.class);
        SupportParam param = new SupportParam(method, new Object[]{Arrays.asList("1222", "3333")});
        DefaultMonitorDefinition defaultMonitorDefinition = new DefaultMonitorDefinition();
        defaultMonitorDefinition.setKey("#{[0]}");
        System.out.println(springELKeyCalculater.calculate(param, defaultMonitorDefinition));


    }

}