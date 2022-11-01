package com.ch.springBean生命周期;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: ch
 * @date: 2022/8/14 15:43
 * @description: TODO
 */
@Slf4j
@Component
public class LifeCycleBean {

    public LifeCycleBean() {
        log.info("构造...");
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String home) {
        log.info("依赖注入: {}", home);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.info("销毁");
    }
}
