package com.my.springboot.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 从配置文件中获取值的示例
 */
@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("dev test");
        return "hello " + name;
    }
}
