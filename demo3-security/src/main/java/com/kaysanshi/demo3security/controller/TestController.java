package com.kaysanshi.demo3security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 注解的方式进行设置
     * @return
     */
    @GetMapping("/hello2")
    @Secured({"ROLE_sale","ROLE_manage"})
    public String hello2() {
        return "hello2";
    }

    /**
     * 进入方法之前进行校验
     * @return
     */
    @GetMapping("/hello3")
    //@PreAuthorize("hasRole('ROLE_管理员')")
    @PreAuthorize("hasAnyAuthority('menu:system')")
    public String hello23() {
        return "hello2";
    }

    /**
     * 进入方法之后进行校验
     * @return
     */
    @GetMapping("/hello3")
    @PostAuthorize("hasAnyAuthority('admins')")
    public String hello231() {
        System.out.println("123...");
        return "hello2";
    }
}

