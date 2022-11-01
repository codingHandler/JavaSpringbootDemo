package com.ch.hotelDemo.mq;

import com.ch.hotelDemo.constants.HotelMqConstants;
import com.ch.hotelDemo.service.IHotelService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/6/4 20:02
 * @description:
 */
@Component
public class HotelListener {


    @Autowired
    private IHotelService hotelService;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = HotelMqConstants.INSERT_QUEUE_NAME,durable = "true"),
                    exchange = @Exchange(name = HotelMqConstants.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = HotelMqConstants.INSERT_KEY
            ))
    public void listenHotelInsert(Long hotelId) {
        // 新增
        hotelService.saveById(hotelId);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = HotelMqConstants.DELETE_QUEUE_NAME, durable = "true"),
                    exchange = @Exchange(name = HotelMqConstants.EXCHANGE_NAME,type = ExchangeTypes.TOPIC),
                    key = HotelMqConstants.DELETE_KEY
            ))
    public void listenHotelDelete(Long hotelId) {
        // 新增
        hotelService.deleteById(hotelId);
    }
}
