package com.kaysanshi.springsecurityoauth2.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.net.www.protocol.http.AuthenticationInfo;

/**
 * Description:
 * spring security配置
 * WebSecurityConfigurerAdapter是默认情况下 Spring security的http配置
 * 优先级高于ResourceServerConfigurer，用于保护oauth相关的endpoints，同时主要作用于用户的登录（form login，Basic auth）
 * @date:2020/10/29 9:41
 * @author: kaysanshi
 **/
@Configuration
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
    /**
     * 为了让认证配置类注入使用
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 自定义认证逻辑时需要实现这个类
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123")) //123
                .roles("admin")
                .and()
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("123")) //123
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**").authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and().csrf().disable();
    }
}
