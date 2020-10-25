package com.kaysanshi.demosecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description:
 *
 * @date:2020/10/23 10:52
 * @author: kaysanshi
 **/
@Configuration
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 在配置类中配置认证的密码与用户
        auth.inMemoryAuthentication()
                .withUser("kay")
                .roles("admin")
                .password(passwordEncoder.encode("123"))
                .and()
        .withUser("kkk")
        .roles("user")
        .password(passwordEncoder.encode("123")); // 这里的password 放置加密后的字符串
    }

    /**
     * 为bean 定义如何解码
     * 如果不使用则会报错。java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
