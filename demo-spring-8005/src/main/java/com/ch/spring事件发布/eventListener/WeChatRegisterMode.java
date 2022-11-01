package com.ch.spring事件发布.eventListener;

import com.ch.spring事件发布.services.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author: ch
 * @date: 2022/8/14 12:06
 * @description: 微信注册方式监听器
 */
@Slf4j
@Component
public class WeChatRegisterMode{


    /***
     * 微信注册方式监听器
     */
    @EventListener
    public void weChatRegister(UserRegisterEvent userRegisterEvent) {
        log.info("收到事件,微信注册方式");
    }
}
