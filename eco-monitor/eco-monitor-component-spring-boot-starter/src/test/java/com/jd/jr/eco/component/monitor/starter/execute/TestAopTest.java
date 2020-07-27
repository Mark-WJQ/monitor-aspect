package com.jd.jr.eco.component.monitor.starter.execute;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


/**
 * @author wangjianqiang24
 * @date 2020/6/15
 */
@SpringBootTest(classes = TestAop.class)
@EnableAutoConfiguration
public class TestAopTest {

    @Autowired
    private TestAop testAop;

    @Test
    public void monitor() {
        testAop.monitor(Arrays.asList("11111","3333"));
    }
}