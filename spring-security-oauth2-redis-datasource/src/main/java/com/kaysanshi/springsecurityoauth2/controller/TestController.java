package com.kaysanshi.springsecurityoauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *  加入了author2的认证，所以要加入token访问资源时
 * @date:2020/10/29 9:46
 * @author: kaysanshi
 **/
@RestController
public class TestController {
    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
