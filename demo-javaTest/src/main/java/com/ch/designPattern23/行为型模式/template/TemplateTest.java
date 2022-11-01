package com.ch.designPattern23.行为型模式.template;

/**
 * @className: TemplateTest
 * @Auther: ch
 * @Date: 2022/3/7 17:48
 * @Description: 模板模式-测试
 */
public class TemplateTest {
    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.templateMethod();
    }
}
