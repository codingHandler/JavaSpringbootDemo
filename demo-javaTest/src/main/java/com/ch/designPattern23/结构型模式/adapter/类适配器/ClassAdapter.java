package com.ch.designPattern23.结构型模式.adapter.类适配器;

/**
 * @className: ClassAdapter
 * @Auther: ch
 * @Date: 2022/5/17 16:36
 * @Description: 类适配器类
 */
public class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
