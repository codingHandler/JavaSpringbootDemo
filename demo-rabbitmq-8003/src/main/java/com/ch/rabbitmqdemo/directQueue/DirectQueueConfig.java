package com.ch.rabbitmqdemo.directQueue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: ch
 * @date: 2022/6/8 16:04
 * @description: direct队列配置
 */
@Configuration
public class DirectQueueConfig {

    /***
     * 创建direct队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        return QueueBuilder.durable("directQueue").build();
    }
    /***
     * 创建direct队列
     * @return
     */
    @Bean
    public Queue directQueue2() {
        return QueueBuilder.durable("directQueue2").build();
    }
    /***
     * 创建direct交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return ExchangeBuilder.directExchange("directExchange").build();
    }
    /***
     * direct队列绑定direct交换机
     * @return
     */
    @Bean
    public Binding directBindingExchange(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directKey");
    }
    /***
     * direct队列绑定direct交换机
     * @return
     */
    @Bean
    public Binding directBindingExchange2(){
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("directKey2");
    }

}
