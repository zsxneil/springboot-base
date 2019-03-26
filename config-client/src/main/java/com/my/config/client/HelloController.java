package com.my.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //通过配置中心取值
    @Value("${nickname}")
    private String nickName;

    //在程序中的配置文件中取值
    @Value("${my.name}")
    private String hi;

    @GetMapping("/hello")
    public String hello() {

        

        return "Hello " + nickName + "; locale.test:" + hi;
    }

}
