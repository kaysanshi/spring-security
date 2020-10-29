package com.kaysanshi.springsecurityoauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring security oauth2集成，
 * token是存在内存中不涉及到redis保存
 *
 */
@SpringBootApplication
@MapperScan("com.kaysanshi.*")
public class SpringSecurityOauth2RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2RedisApplication.class, args);
    }

}
