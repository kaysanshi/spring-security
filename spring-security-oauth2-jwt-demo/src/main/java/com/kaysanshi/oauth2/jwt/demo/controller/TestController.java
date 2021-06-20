package com.kaysanshi.oauth2.jwt.demo.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

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
     * 获取当前用户,并解析这个 需要jjwt依赖
     * @return
     */
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(HttpServletRequest httpRequest, Authentication authentication){
        String header = httpRequest.getHeader("Authorization");
        String token =header.substring(header.indexOf("bearer")+7);

        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }
}
