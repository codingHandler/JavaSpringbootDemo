package com.ch.logbackdemo;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @className: com.ch.logbackdemo.LogbackDemoTest
 * @Auther: ch
 * @Date: 2022/3/16 19:45
 * @Description: logback单元测试
 * 日志级别:
 *  TRACE > DEBUG > INFO > WARN > ERROR
 *
 *
 */
@SpringBootTest
public class LogbackDemoTest {

    /**
     * 入门案例：
     * springboot日志具体实现
     * 测试级别
     * 默认info级别
     * logback的风格输出（默认使用的是logback的日志实现）
     */
    @Test
    public void test1() {
        Logger logger = LoggerFactory.getLogger(LogbackDemoTest.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }


    /***
     * 使用log4j2的日志实现
     * 观察桥接器是否起作用
     * 结果：仍然是slf4j+logback
     * 证明桥接器是起作用的
     *
     */
    @Test
    public void test2() {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogBackDemoMain.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    /***
     * application.properties(yml)是springboot的核心配置文件（用来简化开发）
     * 我们可以通过该配置文件，修改日志相关的配置
     */
    @Test
    public void test3() {
        org.apache.logging.log4j.Logger logger = LogManager.getLogger(LogBackDemoMain.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    @Test
    public void test4() {
        Logger logger=LoggerFactory.getLogger(LogBackDemoMain.class);
        try {
            int i= 1/0;
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error信息",e);
        }
    }

    public void test5(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBean(LogBackDemoMain.class);
    }
}
