package com.kaysanshi.springsecurityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * jwt demo
 */
@SpringBootApplication
public class SpringSecurityOauth2JWTApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2JWTApplication.class, args);
    }

}
