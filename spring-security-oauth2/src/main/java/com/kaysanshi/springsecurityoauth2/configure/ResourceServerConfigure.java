package com.kaysanshi.springsecurityoauth2.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Description:
 * 配置资源服务器 : ResourceServerConfigurerAdapter
 * ResourceServerConfigurerAdapter是默认情况下spring security oauth 的http配置。
 * @date:2020/10/29 9:44
 * @author: kaysanshi
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {
    /**
     * 配置响应资源的访问。
     *
     * @param http
     * @throws Exception
     */
    // 配置 URL 访问权限
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/test/**");
    }
}
