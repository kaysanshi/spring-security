package com.kaysanshi.demo1security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author kay三石
 * @date:2020/10/24
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";

    }

    @GetMapping("/unauth")
    public String accessDenyPage() {
        return "unauth";

    }

}

