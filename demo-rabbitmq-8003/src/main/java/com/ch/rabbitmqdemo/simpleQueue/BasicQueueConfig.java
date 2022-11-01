package com.ch.rabbitmqdemo.simpleQueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: ch
 * @date: 2022/6/7 22:31
 * @description: 简单队列
 */
@Configuration
public class BasicQueueConfig {
    @Bean
    public Queue basicQueue(){
        return QueueBuilder.durable("basicQueue").build();
    }
}
