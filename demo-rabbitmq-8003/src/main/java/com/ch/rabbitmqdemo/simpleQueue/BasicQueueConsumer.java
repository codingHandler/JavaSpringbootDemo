package com.ch.rabbitmqdemo.simpleQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/6/7 22:52
 * @description: 基本对列消费者
 */
@Slf4j
@Component
public class BasicQueueConsumer {

    @RabbitListener(queues = "basicQueue")
    public void Consumer1(Message message) {
        int i = 1/0;
        log.info("消费者消费-->{}", message.toString());
    }


    @RabbitListener(queuesToDeclare = @Queue("basicQueueAnno"))
    public void Consumer2(Message message) {
        log.info("消费者2消费-->{}", message.toString());
    }

}
