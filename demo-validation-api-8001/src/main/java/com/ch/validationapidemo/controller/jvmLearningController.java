package com.ch.validationapidemo.controller;

import com.ch.validationapidemo.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/7/7 11:45
 * @description: TODO
 */
@RestController
@RequestMapping("/jvm")
public class jvmLearningController {

    public static List<Object> list = new ArrayList<>();

    /***
     * JVM设置:
     *  -Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm.dump
     */
    @PostMapping("/testJvm")
    public void testJvm() {
        ArrayList<Object> list = new ArrayList<>();
        while (true){
            list.add(new User());
        }
    }
}
