package com.kaysanshi.oauth2.jwt.demo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

/**
 * Jwt token 配置类配置存储
 * @Author kay三石
 * @date:2020/11/2
 */
@Configuration
public class JwtTokenConfigure {

    @Bean
    public TokenStore jwtTokenStore(){
        // 基于jWT实现的令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter =new JwtAccessTokenConverter();
        // 配置JWt的使用密钥
        accessTokenConverter.setSigningKey("test_key");
        return accessTokenConverter;
    }
}
