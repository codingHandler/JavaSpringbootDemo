package com.ch.testBeanFactory.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ch
 * @date: 2022/8/14 13:54
 * @description: TODO
 */
@Slf4j
public class TestBeanFactory {
    public static void main(String[] args) {
        // beanfactory的最重要的实现类
        // DefaultListableBeanFactory 缺少了解析 @Configuration注解的能力
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean的定义(class,scope,初始化,销毁)
        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        // 将定义好的bean注册到容器中
        beanFactory.registerBeanDefinition("config", beanDefinition);

        // 给BeanFactory添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        // BeanFactory后置处理器主要功能:补充了一些Bean定义
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().stream().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        // Bean的后置处理器,针对bean的生命周期的各个阶段提供扩展,例如@Autowire @Resource...
        beanFactory.getBeansOfType(BeanPostProcessor.class)
                .values()
                .stream()
                .forEach(beanFactory::addBeanPostProcessor);

        // 获取beanfactory中所有bean的名字
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            log.info(beanName);
        }
        // 配置准备好所有单例对象,即加载即创建
        beanFactory.preInstantiateSingletons();
        System.out.println(">>>>>>>>>>>>>>>>>");
        // 默认调用时才创建bean
        Bean2 bean2 = beanFactory.getBean(Bean1.class).getBean2();
        System.out.println(bean2);


        /***
         * 总结:
         * 1,BeanFactory不会做的事情
         *  - 不会主动调用BeanFactory的后置处理器
         *  - 不会主动添加Bean后置处理器
         *  - 不会主动初始化单例对象
         *  - 不会解析BeanFactory 还不会解析${} 和#{}
         * 2,bean后置处理器会有排序的逻辑
         */
    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean1 {
        @Autowired
        private Bean2 bean2;

        public Bean1() {
            log.info("构造 bean1");
        }


        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        @Autowired
        private Bean1 bean1;

        public Bean2() {
            log.info("构造 bean2");
        }
    }
}

