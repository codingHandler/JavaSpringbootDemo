package com.ch.designPattern23.结构型模式.bridging.carDemo;

/**
 * @author admin
 * @className: RefinedCar
 * @Auther: ch
 * @Date: 2022/5/19 11:02
 * @Description: 提供修改
 */
public abstract class BaseCar extends Car {

    public BaseCar(YingQing yingQing) {
        super(yingQing);
    }

    @Override
    public void driver() {
        System.out.print(getBrand() + "使用");
        this.yingQing.start();
    }

    // 定义车的品牌
    public abstract String getBrand();
}
