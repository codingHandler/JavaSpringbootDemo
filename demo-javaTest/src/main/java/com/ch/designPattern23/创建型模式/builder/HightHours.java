package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: HightHours
 * @Auther: ch
 * @Date: 2021/10/11 18:03
 * @Description: 建造者模式-豪华房子建造者
 */
public class HightHours extends HoursBuilder {
    @Override
    void buildWall() {
        hours.setWall("豪华----20米墙");
    }

    @Override
    void buildRoof() {
        hours.setRoof("豪华----琉璃瓦楼顶");
    }

    @Override
    void buildTv() {
        hours.setTV("豪华---液晶彩色电视");
    }

    @Override
    void buildSofa() {
        hours.setSofa("豪华---牛皮沙发");
    }

    @Override
    Hours getHours() {
        System.out.println(hours.toString());
        return hours;
    }
}
