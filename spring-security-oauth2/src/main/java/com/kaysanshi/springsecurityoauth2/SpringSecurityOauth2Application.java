package com.kaysanshi.springsecurityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用密码模式
 * Spring security oauth2集成，token是存在内存中不涉及到redis保存
 */
@SpringBootApplication
public class SpringSecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2Application.class, args);
    }

}
