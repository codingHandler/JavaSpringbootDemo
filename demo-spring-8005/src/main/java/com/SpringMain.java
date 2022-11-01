package com;

import com.ch.spring事件发布.services.UserRegisterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: ch
 * @date: 2022/8/14 10:56
 * @description: BeanFactory 和 ApplicationContext 的区别
 */
@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMain.class);
        /***
         * 1,到底什么是BeanFactory?
         *  - 他是ApplicationContext的父类
         *  - 他才是spring的核心容器,主要的ApplicationContext 实现都[组合]了他的功能
         */
        context.getBean("userRegisterService");

        /***
         * 2,BeanFactory能干嘛?
         *  - 表面上只有getBean
         *  - 实际上控制反转,基本的依赖注入,直至Bean的生命周期的各种功能,都由他的实现类提供
         */
        context.getBean(UserRegisterService.class).register();




        /***
         * 总结:
         *  - BeanFactory 和 ApplicationContext并不仅仅是简单的接口继承关系,ApplicationContext组合并扩展了BeanFactory的功能
         *
         */

    }
}
