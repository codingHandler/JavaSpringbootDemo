package com.ch.designPattern23.创建型模式.factory.simpleDemo;

import java.util.Map;

/**
 * @className: ICommodity
 * @Auther: ch
 * @Date: 2021/12/30 17:47
 * @Description: TODO
 */
public interface ICommodity {
    void sendCommodity(String uid, String commodityId, String bizId, Map<String,String> extMap) throws Exception;
}
