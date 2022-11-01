package com.ch.spring事件发布.services;

import org.springframework.context.ApplicationEvent;

/**
 * @author: ch
 * @date: 2022/8/14 11:50
 * @description: 用户注册事件发布类
 */
public class UserRegisterEvent extends ApplicationEvent {
    /***
     * @param source 事件源,谁发的事件
     */
    public UserRegisterEvent(Object source) {
        super(source);
    }
}
