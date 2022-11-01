package com.ch.springBean生命周期;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/8/14 15:43
 * @description: 自定义bean的后置处理器
 */
@Slf4j
//@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    /************************销毁之前****************************/
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("<<< 销毁之前执行,如 PreDestroy");
        }
    }

    /************************实例化之前,实例化之后****************************/
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("<<< 实例化之前执行,这里返回的对象会替换掉原本的bean");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("<<< 实例化之后执行,如果返回false会跳过依赖注入阶段");
            //return false;
        }
        return true;
    }

    /************************依赖注入****************************/

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("<<<依赖注入阶段执行,如@Autowired,@Value,@Resource");
        }

        return pvs;
    }


    /************************初始化之前,初始化之后****************************/

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("初始化之前执行,这里返回的对象会替换原本的bean,如@PostConstruct,@ConfigurationProperties");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("lifeCycleBean")) {
            log.info("初始化之后执行,这里返回的对象会替换原本的bean,如代理增强");
        }
        return bean;
    }
}
