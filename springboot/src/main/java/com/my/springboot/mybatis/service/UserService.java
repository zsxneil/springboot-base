package com.my.springboot.mybatis.service;

import com.my.springboot.mybatis.model.User;

import java.util.List;

public interface UserService {
    int insert(User user);
    int update(User user);
    List<User> selectAll();
    int delete(User user);
    User selectOne(int id);
}
