package com.ch.common.exception;

/**
 * @className: CustomException
 * @Auther: ch
 * @Date: 2022/4/2 10:31
 * @Description: 自定义异常
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
