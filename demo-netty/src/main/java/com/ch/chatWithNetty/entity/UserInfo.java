package com.ch.chatWithNetty.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ch
 * @date: 2022/8/26 16:02
 * @description: TODO
 */
@Data
public class UserInfo implements Serializable {

    private String username;

    private String password;

    private Integer sex;

    private Long birthday;

    private Integer age;

}
