package com.ch.designPattern23.行为型模式.strategy.carDemo;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2022/5/18 22:06
 * @Description: 策略模式
 */
public class Test {
    public static void main(String[] args) {
        CarContext context = new CarContext();
        context.setCarName("五菱宏光");
        context.setCarStrategy(new YouCarStrategy());
        context.run();

        context.setCarStrategy(new DianCarStrategy());
        context.setCarName("特斯拉");
        context.run();
    }
}
