package com.ch.泛型.泛型接口;

/**
 * @author: ch
 * @date: 2022/6/12 16:16
 * @description: 实现泛型接口的类, 不是泛型, 需要明确实现
 */
public class GenericInterfaceImpl implements GenericInterface<String> {
    @Override
    public String getKey() {
        return "hello generic";
    }
}
