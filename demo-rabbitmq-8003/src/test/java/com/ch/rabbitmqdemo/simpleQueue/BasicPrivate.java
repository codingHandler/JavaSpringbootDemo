package com.ch.rabbitmqdemo.simpleQueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author: ch
 * @date: 2022/6/7 23:10
 * @description: TODO
 */
@Slf4j
@SpringBootTest
public class BasicPrivate {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void basicQueueTest() {
        rabbitTemplate.convertAndSend("basicQueue", "hello world -->1");
        //rabbitTemplate.convertAndSend("basicQueueAnno", "hello world -->1");
    }


    @Test
    public void workerQueueTest() {
        rabbitTemplate.convertAndSend("basicQueue", "hello world -->1");
        rabbitTemplate.convertAndSend("basicQueue", "hello world -->1");
        rabbitTemplate.convertAndSend("basicQueueAnno", "hello world -->1");
    }

    @Test
    public void fanoutQueueTest() {
        rabbitTemplate.convertAndSend("fanoutExchange", "", "hello world -->1");
        rabbitTemplate.convertAndSend("fanoutExchange", "", "hello world -->2");
        //rabbitTemplate.convertAndSend("fanoutExchangeAnno","","hello world -->1");
    }

    @Test
    public void directQueueTest() {
        rabbitTemplate.convertAndSend("directExchange", "directKey", "hello world -->1");
        rabbitTemplate.convertAndSend("directExchange", "directKey2", "hello world -->2");
        //rabbitTemplate.convertAndSend("fanoutExchangeAnno","","hello world -->1");
    }

    @Test
    public void topicQueueTest() {
        rabbitTemplate.convertAndSend("topicExchange", "topic.red", "hello world -->1");  // 1收 2收
        rabbitTemplate.convertAndSend("topicExchange", "topic.red.blue", "hello world -->2"); // 2收
    }



    // 测试生产者确认/失败策略
    @Test
    public void directQueueTest2() {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("directExchange", "directKey", "hello world -->1",correlationData);
        // rabbitTemplate.convertAndSend("directExchange", "directKey2", "hello world -->2",correlationData);
        //rabbitTemplate.convertAndSend("fanoutExchangeAnno","","hello world -->1");
    }

}
