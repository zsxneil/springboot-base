package com.my.springboot.mybatis.service.impl;

import com.my.springboot.mybatis.mappers.UserMapper;
import com.my.springboot.mybatis.model.User;
import com.my.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    //@Transactional(readOnly = true)
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int delete(User user) {
        return userMapper.delete(user);
    }

    @Override
    //@Transactional(readOnly = false)
    public User selectOne(int id) {
        User user = userMapper.selectOne(id);
        user.setUsername("txtest");
        userMapper.update(user);
        return userMapper.selectOne(id);
    }
}
