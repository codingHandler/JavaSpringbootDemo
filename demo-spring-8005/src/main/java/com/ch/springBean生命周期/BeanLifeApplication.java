package com.ch.springBean生命周期;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: ch
 * @date: 2022/8/14 15:42
 * @description: TODO
 */
@SpringBootApplication
public class BeanLifeApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run =
                SpringApplication.run(BeanLifeApplication.class, args);
        Object lifeCycleBean = run.getBean("lifeCycleBean");
        run.close();
    }
}
