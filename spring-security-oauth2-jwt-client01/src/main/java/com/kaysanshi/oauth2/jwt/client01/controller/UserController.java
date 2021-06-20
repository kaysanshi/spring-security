package com.kaysanshi.oauth2.jwt.client01.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    /**
     * 获取当前用户信息
     * @param authentication
     * @return
     */
    @GetMapping("getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
       return authentication;
    }
}
