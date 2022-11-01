package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: HoursBuilder
 * @Auther: ch
 * @Date: 2021/10/11 17:48
 * @Description: 建造者模式-抽象建造者
 */
public abstract class HoursBuilder {

    protected Hours hours = new Hours();

    // 墙
    abstract void buildWall();

    // 楼顶
    abstract void buildRoof();

    // 电视机
    abstract void buildTv();

    // 沙发
    abstract void buildSofa();

    // 完工收房
    abstract Hours getHours();

}
