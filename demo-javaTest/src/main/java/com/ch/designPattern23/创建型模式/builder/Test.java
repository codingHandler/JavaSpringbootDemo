package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2021/10/12 10:51
 * @Description: TODO
 */
public class Test {
    public static void main(String[] args) {
        HoursBuilder hoursBuilder = new HightHours();
        HouseDecorator director = new HouseDecorator(hoursBuilder);
    }
}
