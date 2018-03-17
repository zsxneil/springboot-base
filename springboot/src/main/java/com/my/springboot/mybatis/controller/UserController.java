package com.my.springboot.mybatis.controller;

import com.my.springboot.mybatis.model.User;
import com.my.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController
{
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/mybatis/user")
    public User findUser() {
        User user = userService.selectOne(1);
        return user;
    }

    @ResponseBody
    @RequestMapping("/mybatis/insert")
    public User insertUser() {
        User user = new User();
        user.setUsername("Neil");
        user.setBirthday(new Date());
        user.setAddress("深圳市");
        user.setSex(2);

        userService.insert(user);
        int id = user.getId();
        return user;
    }
}
