package com.jd.jr.eco.component.monitor.starter.execute;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author wangjianqiang24
 * @date 2020/6/15
 */
@SpringBootTest(classes = TestAop.class)
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
public class TestAopTest {

    @Autowired
    private TestAop testAop;

    @Test
    public void monitor() {
        testAop.monitor(null);
    }
}