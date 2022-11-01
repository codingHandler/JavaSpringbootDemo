package com.ch.黑马.lambdaTest;

/**
 * @author: ch
 * @date: 2022/8/22 16:07
 * @description: TODO
 */
@FunctionalInterface
public interface GenerateSecretKey<T> {

    T createSecretKey();

}
