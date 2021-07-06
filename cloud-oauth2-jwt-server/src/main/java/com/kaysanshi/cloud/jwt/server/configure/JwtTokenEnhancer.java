package com.kaysanshi.cloud.jwt.server.configure;

import com.kaysanshi.cloud.jwt.server.pojo.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT 内容增强器
 *
 * @Author kay三石
 * @date:2020/11/5
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 自定义内容增强,将用户信息增强到里面
        Map <String, Object> info = new HashMap <>();
        User userDTO = (User) authentication.getPrincipal();
        info.put("userName", userDTO.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
