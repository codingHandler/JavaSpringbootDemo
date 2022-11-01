package com.ch.rabbitmqdemo.directQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: ch
 * @date: 2022/6/8 16:43
 * @description: TODO
 */
@Slf4j
@Component
public class DirectConsumer {

    @RabbitListener(queues = "directQueue")
    public void directConsumer(Message message) {
        log.info("directQueue队列消费者消费消息--->{}", message.toString());
    }

    @RabbitListener(queues = "directQueue2")
    public void directConsumer2(Message message) {
        log.info("directQueue2队列消费者消费消息--->{}", message.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "directAnnoQueue"),
            exchange= @Exchange(value = "directAnnoExchange", type = ExchangeTypes.DIRECT),
            key = {"red"}))
    public void directAnnoConsumer(Message message) {
        log.info("directQueue队列消费者消费消息--->{}", message.toString());
    }


}
