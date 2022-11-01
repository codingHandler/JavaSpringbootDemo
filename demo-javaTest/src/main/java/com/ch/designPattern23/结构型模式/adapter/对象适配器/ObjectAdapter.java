package com.ch.designPattern23.结构型模式.adapter.对象适配器;

import com.ch.designPattern23.结构型模式.adapter.类适配器.Adaptee;
import com.ch.designPattern23.结构型模式.adapter.类适配器.Target;

/**
 * @className: ObjectAdapter
 * @Auther: ch
 * @Date: 2022/5/17 16:40
 * @Description: 对象适配器类
 */
public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
