package com.ch.泛型.类型通配符;

/**
 * @author: ch
 * @date: 2022/6/12 17:09
 * @description: 泛型统配符类
 */
public class GenericWildcards<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
