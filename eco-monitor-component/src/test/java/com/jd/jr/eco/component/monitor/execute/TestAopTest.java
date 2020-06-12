package com.jd.jr.eco.component.monitor.execute;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wangjianqiang24
 * @date 2020/6/12
 */
@SpringBootTest(classes = TestAop.class)
@EnableAutoConfiguration
class TestAopTest {

    @Autowired
    private TestAop testAop;

    @Test
    void monitor() {
        testAop.monitor(null);
    }
}