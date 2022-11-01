package com.ch.designPattern23.结构型模式.adapter.类适配器;

/**
 * @className: Adaptee
 * @Auther: ch
 * @Date: 2022/5/17 16:35
 * @Description: 适配者接口
 */
public class Adaptee {
    public void specificRequest()
    {
        System.out.println("适配者中的业务代码被调用！");
    }
}
