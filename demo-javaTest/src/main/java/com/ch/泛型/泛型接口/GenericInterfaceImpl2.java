package com.ch.泛型.泛型接口;

/**
 * @author: ch
 * @date: 2022/6/12 16:22
 * @description: 泛型接口的实现类是一个泛型类, 那么要保证实现接口的泛型类泛型标识包含泛型接口的泛型标识
 */
public class GenericInterfaceImpl2<T, E> implements GenericInterface<T> {
    private E value;
    private T key;


    public GenericInterfaceImpl2(E value, T key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public T getKey() {
        return null;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
