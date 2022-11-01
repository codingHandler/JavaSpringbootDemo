package com.ch.泛型.泛型派生子类;

/**
 * @author: ch
 * @date: 2022/6/12 16:02
 * @description: 泛型类派生子类,子类也是泛型类,那么子类的泛型标识要和父类一直
 */
public class ChildFirst<T> extends Parent<T>{

    @Override
    public T getValue() {
        return super.getValue();
    }
}
