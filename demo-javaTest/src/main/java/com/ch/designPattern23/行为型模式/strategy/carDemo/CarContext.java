package com.ch.designPattern23.行为型模式.strategy.carDemo;

/**
 * @className: CarContext
 * @Auther: ch
 * @Date: 2022/5/18 22:05
 * @Description: 环境类
 */

public class CarContext {
    private String carName;

    private CarStrategy carStrategy;

    public CarStrategy getCarStrategy() {
        return carStrategy;
    }

    public void setCarStrategy(CarStrategy carStrategy) {
        this.carStrategy = carStrategy;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void run(){
        System.out.print(this.carName +" : ");
        carStrategy.fuel();
    }
}
