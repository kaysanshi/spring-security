package com.kaysanshi.demo1security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Demo1SecurityApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testBCryptPasswordEncoder() {
        // 創建密碼解析器
        BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
        // 對密碼進行加密
        String kay=bCryptPasswordEncoder.encode("kay");

        System.out.println(kay);

        // 判斷原字符串
        boolean result = bCryptPasswordEncoder.matches("kay",kay);

        System.out.println(result);
    }

}
