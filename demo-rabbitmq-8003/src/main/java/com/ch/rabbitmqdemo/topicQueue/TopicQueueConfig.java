package com.ch.rabbitmqdemo.topicQueue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author: ch
 * @date: 2022/6/8 16:57
 * @description: topic队列配置
 */
@Configuration
public class TopicQueueConfig {
    @Bean
    public Queue topicQueue(){
        return QueueBuilder.durable("topicQueue").build();
    }

    @Bean
    public Queue topicQueue2(){
        return QueueBuilder.durable("topicQueue2").build();
    }

    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange("topicExchange").build();
    }

    @Bean
    public Binding topicBindingQueue(){
        //  # ：匹配一个或多个词
        //  * * ：匹配不多不少恰好1个词
        // item.# ：能够匹配 item.spu.insert  item.spu
        // item.* ：只能匹配 item.spu
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.*");
    }
    @Bean
    public Binding topicBindingQueue2(){
        //  # ：匹配一个或多个词
        //  * * ：匹配不多不少恰好1个词
        // item.# ：能够匹配 item.spu.insert  item.spu
        // item.* ：只能匹配 item.spu
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }
}
