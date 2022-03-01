package com.ch.validationapidemo.services.impl;

import com.ch.validationapidemo.entity.User;
import com.ch.validationapidemo.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @className: UserService
 * @Auther: ch
 * @Date: 2022/2/28 16:43
 * @Description: 用户服务层实现
 */
@Service
public class UserService implements IUserService {
    /**
     * 模拟数据库
     */
    ArrayList<User> userLists = new ArrayList<>();

    @Override
    public int add(User user) {
        return userLists.add(user) ? 1 : 0;
    }

    @Override
    public ArrayList<User> selectAll(User user) {
        return userLists;
    }

}
