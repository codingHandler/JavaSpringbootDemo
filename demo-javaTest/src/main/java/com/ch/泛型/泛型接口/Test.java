package com.ch.泛型.泛型接口;

/**
 * @author: ch
 * @date: 2022/6/12 16:20
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {

        GenericInterfaceImpl stringGenericInterface1 = new GenericInterfaceImpl();
        String key1 = stringGenericInterface1.getKey();
        System.out.println(key1);
    }
}
