package com.ch.rabbitmqdemo.failureStrategy;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ch
 * @date: 2022/6/9 13:52
 * @description: 失败策略
 * 开启重试模式后，重试次数耗尽，如果消息依然失败，则需要有MessageRecovery接口来处理，
 * 它包含三种不同的实现：
 * RejectAndDontRequeueRecoverer：重试耗尽后，直接reject，丢弃消息。默认就是这种方式
 * ImmediateRequeueMessageRecoverer：重试耗尽后，返回nack，消息重新入队
 * RepublishMessageRecoverer：重试耗尽后，将失败消息投递到指定的交换机
 */

@Configuration
public class ErrorMessageConfig {
    @Bean
    public DirectExchange errorMessageExchange() {
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("error.queue", true);
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange errorMessageExchange) {
        return BindingBuilder.bind(errorQueue).to(errorMessageExchange).with("error");
    }


    // 定义一个RepublishMessageRecoverer，关联队列和交换机
    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }


}
