package com.ch.designPattern23.创建型模式.factory.simple;

/**
 * @className: CarFactory
 * @Auther: ch
 * @Date: 2021/5/19 15:34
 * @Description: TODO
 */
public class CarFactory {
    public static Car getCar(String car){
        switch (car){
            case "五菱":
                return new WuLing();
            case "特斯拉":
                return new TeSiLa();
            default:
                return null;
        }
    }
}
