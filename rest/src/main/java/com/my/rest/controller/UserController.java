package com.my.rest.controller;

import com.my.rest.model.Role;
import com.my.rest.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user/1")
    public ResponseEntity<User> findById(@RequestParam("type") String type) {
        if (type.equals("err"))
            return ResponseEntity.notFound().build();
        User user = new User();
        user.setUsername("username");
        user.setAge(17);
        Role role = new Role();
        role.setId(1);
        role.setCode("code1");
        Role role1 = new Role();
        role1.setId(2);
        role1.setCode("code2");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        roleList.add(role1);
        user.setRoleList(roleList);
        return ResponseEntity.ok(user);

    }

}
