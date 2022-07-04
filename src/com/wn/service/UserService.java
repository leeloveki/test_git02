package com.wn.service;


import com.wn.bean.User;

/**
 * 用户业务层
 */
public interface UserService {
    /**
     * 登录
     */
    boolean login(User user);

    /**
     * 注册
     */

    boolean register(User user);
}
