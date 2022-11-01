package com.ch.chatWithNetty.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ch
 * @date: 2022/8/26 16:28
 * @description: 单聊实体类
 */
@Data
public class OneToOneChatEntity extends Message implements Serializable {
    /***
     * 来自谁
     */
    private UserInfo fromUser;

    /***
     * 发送给谁
     */
    private UserInfo toUser;
}
