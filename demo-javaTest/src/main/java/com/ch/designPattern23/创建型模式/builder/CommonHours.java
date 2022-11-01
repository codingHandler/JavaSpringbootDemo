package com.ch.designPattern23.创建型模式.builder;

/**
 * @className: CommonHours
 * @Auther: ch
 * @Date: 2021/10/11 17:53
 * @Description: 建造者模式-普通房子建造者
 */
public class CommonHours extends HoursBuilder {
    @Override
    void buildWall() {
        hours.setWall("普通----5米墙");
    }

    @Override
    void buildRoof() {
        hours.setRoof("普通----楼顶");
    }

    @Override
    void buildTv() {
        hours.setTV("普通---黑白电视");
    }

    @Override
    void buildSofa() {
        hours.setSofa("普通---简版沙发");
    }

    @Override
    Hours getHours() {
        return hours;
    }
}
