package com.ch.泛型;

/**
 * @author: ch
 * @date: 2022/6/12 15:43
 * @param <T> 泛型标识--类型形参
 * @description: 泛型类--创建对象的时候指定具体的数据类型
 */
public class Generic<T> {

    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
