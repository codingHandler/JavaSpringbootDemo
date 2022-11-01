package com.ch.spring事件发布.services;


import com.ch.spring事件发布.eventListener.WeChatRegisterMode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserRegisterService {

    // 事件发布器
    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;


    public void register() {
        log.info("用户注册");
        applicationEventPublisher.publishEvent(new UserRegisterEvent(new WeChatRegisterMode()));
        log.info("用户注册完毕");
    }

}

