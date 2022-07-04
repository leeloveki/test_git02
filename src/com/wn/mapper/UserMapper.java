package com.wn.mapper;

import com.wn.bean.User;

public interface UserMapper {
    Integer register(User user);
    User login(User user);
    Integer isUsernameExist(String username);
}
