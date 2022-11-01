package com.ch.rabbitmqdemo.fanoutQueue;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: ch
 * @date: 2022/6/8 14:43
 * @description: fanout广播配置
 */
@Configuration
public class FanoutQueueConfig {


    /***
     * 创建fanout队列
     * @return
     */
    @Bean
    public Queue fanoutQueue2() {
        return QueueBuilder.durable("fanoutQueue2").build();
    }

    /***
     * 创建fanout队列
     * @return
     */
    @Bean
    public Queue fanoutQueue() {
        return QueueBuilder.durable("fanoutQueue").build();
    }

    /***
     * 创建fanout交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("fanoutExchange").build();
    }

    /***
     * fanout1队列绑定fanout交换机
     * @return
     */
    @Bean
    public Binding fanoutQueueBindingExchange() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }
    /***
     * fanout2队列绑定fanout交换机
     * @return
     */
    @Bean
    public Binding fanoutQueueBindingExchange2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
