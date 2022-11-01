package com.ch.rabbitmqdemo.failureStrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

/**
 * @author: ch
 * @date: 2022/6/9 13:56
 * @description: 失败策略对列消费者
 */
@Slf4j
@Component
public class ErrorMessageConsumer {
    @RabbitListener(queues = "error.queue")
    public void errorMessageConsumer(String message){
      log.info("失败策略执行---->{}",message.toString());
    }
}
