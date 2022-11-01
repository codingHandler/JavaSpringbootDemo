package com.ch.hotelDemo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;
    private List<Hotel> hotelList;

    public PageResult() {
    }

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }

    public PageResult(List<Hotel> hotelList, Long total) {
        this.total = total;
        this.hotelList = hotelList;
    }
}
