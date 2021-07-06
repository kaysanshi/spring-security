package com.kaysanshi.cloud.jwt.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *  加入了author2的认证，所以要加入token访问资源时
 * @date:2020/10/29 9:46
 * @author: kaysanshi
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 获取当前用户
     * @return
     */
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication){
        return authentication.getPrincipal();
    }
}
