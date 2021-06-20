package com.kaysanshi.oauth2.jwt.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * user:kay三石
 * time: 14:07
 * desc:
 **/
@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping("")
    public String get(){
        return "success";
    }
}
