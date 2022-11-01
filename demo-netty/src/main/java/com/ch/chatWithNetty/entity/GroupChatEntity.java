package com.ch.chatWithNetty.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ch
 * @date: 2022/8/26 16:06
 * @description: 组发送实体类
 */
@Data
public class GroupChatEntity extends Message implements Serializable {

    private UserInfo fromUser;

    private GroupInfo groupInfo;
}
