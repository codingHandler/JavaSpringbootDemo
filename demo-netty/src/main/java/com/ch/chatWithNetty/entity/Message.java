package com.ch.chatWithNetty.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ch
 * @date: 2022/8/26 18:10
 * @description: TODO
 */
@Data
public class Message implements Serializable {
    private String msg;
}
