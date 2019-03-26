package com.my.mybatisplus.service.impl;

import com.my.mybatisplus.entity.User;
import com.my.mybatisplus.dao.UserMapper;
import com.my.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsxneil
 * @since 2019-03-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
