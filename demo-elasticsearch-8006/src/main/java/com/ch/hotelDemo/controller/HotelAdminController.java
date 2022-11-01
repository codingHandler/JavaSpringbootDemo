package com.ch.hotelDemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch.hotelDemo.constants.HotelMqConstants;
import com.ch.hotelDemo.pojo.Hotel;
import com.ch.hotelDemo.pojo.PageResult;
import com.ch.hotelDemo.service.IHotelService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;

/**
 * @author: ch
 * @date: 2022/6/4 16:58
 * @description: TODO
 */
@RestController
@RequestMapping("hotelAdmin")
public class HotelAdminController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/{id}")
    public Hotel queryById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    @GetMapping("/list")
    public PageResult hotelList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "1") Integer size) {
        Page<Hotel> result = hotelService.page(new Page<>(page, size));
        return new PageResult(result.getRecords(), result.getTotal());
    }

    @PostMapping
    public void saveHotel(@RequestBody Hotel hotel) {


        hotelService.save(hotel);
        // 发布消息
        rabbitTemplate.convertAndSend(
                HotelMqConstants.EXCHANGE_NAME,
                HotelMqConstants.INSERT_KEY,
                hotel.getId());
    }

    @PutMapping()
    public void updateById(@RequestBody Hotel hotel) {
        if (hotel.getId() == null) {
            throw new InvalidParameterException("id不能为空");
        }
        hotelService.updateById(hotel);
        // 发布消息
        rabbitTemplate.convertAndSend(
                HotelMqConstants.EXCHANGE_NAME,
                HotelMqConstants.INSERT_KEY,
                hotel.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        hotelService.removeById(id);
        // 发布消息
        rabbitTemplate.convertAndSend(HotelMqConstants.EXCHANGE_NAME,
                HotelMqConstants.DELETE_KEY,
                id);
    }
}

