package com.ch.rabbitmqdemo.fanoutQueue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/6/8 15:00
 * @description: fanout广播消费者
 */
@Slf4j
@Component
public class FanoutConsumer {

    @RabbitListener(queues = "fanoutQueue")
    public void fanoutQueueConsumer(Message message) {
        log.info("fanoutQueue队列消费者消费消息--->{}", message.toString());
    }

    @RabbitListener(queues = "fanoutQueue2")
    public void fanoutQueue2Consumer(Message message) {
        log.info("fanoutQueue2队列消费者消费消息 --->{}", message.toString());
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanoutQueueAnno"),
            exchange = @Exchange(name = "fanoutExchangeAnno", type = ExchangeTypes.FANOUT)))
    public void fanoutQueueConsumerAnno(Message message) {
        log.info("fanoutAnno广播消费者消费消息--->{}", message.toString());
    }
}
