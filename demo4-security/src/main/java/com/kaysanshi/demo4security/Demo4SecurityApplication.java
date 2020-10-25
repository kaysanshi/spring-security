package com.kaysanshi.demo4security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主要用來通过编写自定义实现类來實現認證
 */
@SpringBootApplication
@MapperScan("com.kaysanshi.demo4security.*")
public class Demo4SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo4SecurityApplication.class, args);
    }

}
