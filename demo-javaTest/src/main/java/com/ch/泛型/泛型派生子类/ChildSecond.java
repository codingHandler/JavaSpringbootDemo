package com.ch.泛型.泛型派生子类;

/**
 * @author: ch
 * @date: 2022/6/12 16:06
 * @description: 泛型类派生子类,如果子类不是泛型类,那么父类要明确数据类型
 */
public class ChildSecond extends Parent<Integer>{
    @Override
    public Integer getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }
}
