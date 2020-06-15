package com.jd.jr.eco.component.monitor.starter.execute;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
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
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger("root");
        logger.setLevel(Level.DEBUG);
        testAop.monitor(null);
    }
}