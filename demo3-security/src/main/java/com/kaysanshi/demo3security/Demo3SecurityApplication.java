package com.kaysanshi.demo3security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class Demo3SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo3SecurityApplication.class, args);
    }

}
