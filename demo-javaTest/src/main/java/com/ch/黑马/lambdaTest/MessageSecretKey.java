package com.ch.黑马.lambdaTest;

import java.util.List;

/**
 * @author: ch
 * @date: 2022/8/22 16:10
 * @description: TODO
 */
public class MessageSecretKey {
    public static void main(String[] args) {
        new MessageSecretKey().createSecretKey(()->{

            return "你好";
        }, null, null);
    }

    public Object createSecretKey(GenerateSecretKey<?> generateSecretKey, List<String> excludeFields, List<String> includeFields) {
        System.out.println("值: "+generateSecretKey.createSecretKey().toString());
        return null;
    }

}
