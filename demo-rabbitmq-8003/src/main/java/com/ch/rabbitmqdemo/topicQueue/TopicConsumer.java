package com.ch.rabbitmqdemo.topicQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/6/8 17:01
 * @description: TODO
 */
@Slf4j
@Component
public class TopicConsumer {

    @RabbitListener(queues = "topicQueue")
    public void topicQueueConsumer(Message message) {
        log.info("topicQueue队列消费者消费-->{}", message.toString());
    }

    @RabbitListener(queues = "topicQueue2")
    public void topicQueue2Consumer(Message message) {
        log.info("topicQueue2队列消费者消费-->{}", message.toString());
    }
}
