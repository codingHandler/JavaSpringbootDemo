package com.ch.rabbitmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @author: ch
 * @date: 2022/6/8 17:32
 * @description: TODO
 */
@Slf4j
@Configuration
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
        // 指定 ReturnsCallback
        rabbitTemplate.setReturnsCallback(this);
    }

    // 消息到达服务器成功或者失败触发
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            if (correlationData!=null){
                log.info("消息发送到MQ服务器成功,消息ID为:{}", correlationData.getId());
            }
            log.info("消息处理成功:{}",correlationData);
        } else {
            log.error("消息发送到MQ服务器失败,消息ID为:{},失败原因:{}", correlationData.getId(), cause);
        }
    }

    // 消息到达MQ服务器,但路由失败触发
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        System.out.println(returned);
        log.info("消息内容:{},交换机:{},路由键:{}", returned.getMessage(), returned.getExchange(), returned.getRoutingKey());
    }

    /***
     * MQ默认使用JDK序列化方式,JDK序列化方式并不合适。我们希望消息体的体积更小、可读性更高，
     * 因此可以使用JSON方式来做序列化和反序列化。
     */
    // 配置消息转换器。
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
