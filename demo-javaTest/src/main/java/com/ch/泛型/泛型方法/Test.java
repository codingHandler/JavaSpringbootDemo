package com.ch.泛型.泛型方法;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/6/12 16:33
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        GenericMethod genericMethod = new GenericMethod();
        String[] prize = {"冰箱","洗衣机","苹果手机","华为手机"};
        for (int i = 0; i < 10; i++) {
            String value = genericMethod.getValue(Arrays.asList(prize));
            System.out.println(value);
        }
    }
}
