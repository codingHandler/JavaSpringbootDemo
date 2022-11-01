package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: Decorator
 * @Auther: ch
 * @Date: 2021/10/11 18:06
 * @Description: TODO
 */
public class HouseDecorator {
    public HouseDecorator(HoursBuilder hoursBuilder) {
        hoursBuilder.buildWall();
        hoursBuilder.buildRoof();
        hoursBuilder.buildTv();
        hoursBuilder.buildSofa();
        hoursBuilder.getHours();
    }
}
