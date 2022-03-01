package com.ch.validationapidemo.services;

import com.ch.validationapidemo.entity.User;

import java.util.ArrayList;

/**
 * @className: IUserServices
 * @Auther: ch
 * @Date: 2022/2/28 16:43
 * @Description: 用户服务层
 */
public interface IUserService {

    public int add(User user);

    public ArrayList<User> selectAll(User user);
}
