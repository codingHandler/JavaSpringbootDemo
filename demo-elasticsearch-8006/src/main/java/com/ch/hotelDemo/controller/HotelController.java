package com.ch.hotelDemo.controller;

import com.ch.hotelDemo.pojo.HotelDoc;
import com.ch.hotelDemo.pojo.PageResult;
import com.ch.hotelDemo.pojo.RequestParams;
import com.ch.hotelDemo.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: ch
 * @date: 2022/5/31 09:59
 * @description: TODO
 */
@RequestMapping("/hotel")
@RestController
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @PostMapping("/list")
    private PageResult list(@RequestBody RequestParams requestParams) {
        return hotelService.search(requestParams);
    }


    @PostMapping("/filters")
    private Map<String, List<String>> filters(@RequestBody RequestParams requestParams) {
        return hotelService.filters(requestParams);
    }


}
