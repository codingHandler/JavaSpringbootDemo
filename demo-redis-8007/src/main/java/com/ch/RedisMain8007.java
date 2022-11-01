package com.ch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: ch
 * @date: 2022/8/16 17:32
 * @description: redis分布式锁
 */
@SpringBootApplication
public class RedisMain8007 {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisMain8007.class, args);
    }
}
