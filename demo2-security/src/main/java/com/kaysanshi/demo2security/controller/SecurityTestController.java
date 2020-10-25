package com.kaysanshi.demo2security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @date:2020/10/23 10:18
 * @author: kaysanshi
 **/
@RestController
public class SecurityTestController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
