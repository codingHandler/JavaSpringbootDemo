package com.ch.hotelDemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ch.hotelDemo.pojo.Hotel;
import com.ch.hotelDemo.pojo.PageResult;
import com.ch.hotelDemo.pojo.RequestParams;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHotelService extends IService<Hotel> {

    PageResult search(RequestParams requestParams);

    Map<String, List<String>> filters(RequestParams requestParams);

    int saveById(Long id);

    int deleteById(Long id);
}
