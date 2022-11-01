package com.ch.designPattern23.行为型模式.template;

/**
 * @className: AbstractClass
 * @Auther: ch
 * @Date: 2022/3/7 17:42
 * @Description: 抽象类
 */

public abstract class AbstractClass {
    /** 模板方法 */
    public void templateMethod(){
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }
    /** 具体方法 */
    public void specificMethod(){
        System.out.println("抽象方法中的具体方法被调用。。。");
    }
    /** 抽象方法1 */
    abstract void abstractMethod1();

    /** 抽象方法2 */
    abstract void abstractMethod2();
}
