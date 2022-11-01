package com.ch.泛型.泛型派生子类;

/**
 * @author: ch
 * @date: 2022/6/12 16:01
 * @description: TODO
 */
public class Parent<E> {
    private E value;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
