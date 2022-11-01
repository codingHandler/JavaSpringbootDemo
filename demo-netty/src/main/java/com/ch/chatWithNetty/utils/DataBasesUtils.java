package com.ch.chatWithNetty.utils;

import com.ch.chatWithNetty.entity.GroupInfo;
import com.ch.chatWithNetty.entity.UserInfo;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/8/26 16:53
 * @description: 模拟数据库
 */
public class DataBasesUtils {

    /**
     * 保存所有群组 {groupId:[{userId:channelInfo}]}
     */
    public static final HashMap<String, List<HashMap<String, Channel>>> groupMap = new HashMap<>();

    /**
     * 保存所有用户的通道信息 {userId:channelInfo}
     */
    public static final HashMap<String, Channel> userChannelMap = new HashMap<>();

}
