package com.kaysanshi.demo4security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author kay三石
 * @date:2020/10/25
 */
@Controller
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    /**
     * @ResponseBody返回Json串不走视图解析器
     * @return
     */
    @GetMapping("/log")
    @ResponseBody
    public String log(){
        return "login";
    }
}
