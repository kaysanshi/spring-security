package com.kaysanshi.springsecurityoauth2demo.configure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Description: 授权服务器的配置
 * author2配置
 * AuthorizationServerConfigurerAdapter 包括：
 * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
 * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
 *
 * @date:2020/10/29 9:30
 * @author: kaysanshi
 **/
@Configuration
@EnableAuthorizationServer // 开启认证授权服务器
public class AuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置密码加密，因为再UserDetailsService是依赖与这个类的。
     *
     * @return
     */
    // 指定密码的加密方式
    @Autowired
    private PasswordEncoder passwordEncode;

    /**
     * ClientDetailsServiceConfigurer
     * 主要是注入ClientDetailsService实例对象（唯一配置注入）。其它地方可以通过ClientDetailsServiceConfigurer调用开发配置的ClientDetailsService。
     * 系统提供的二个ClientDetailsService实现类：JdbcClientDetailsService、InMemoryClientDetailsService。
     * 演示提供了用户名和密码
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // 配置一个基于client认证的。
        clients.inMemory()
                // 配置clientId
                .withClient("admin")
                // 配置client-secret
                .secret(passwordEncode.encode("112233"))
                // 配置token过期时间
                .accessTokenValiditySeconds(2630)
                .refreshTokenValiditySeconds(864000)
                // 配置 redirectUri，用于授权成功后跳转
                .redirectUris("http://127.0.0.1:8083/user/getCurrentUser")
                // 自动授权
                .autoApprove(true)
                // 配置申请的权限范围
                .scopes("all")
                // 配置grant_type 表示授权类型。 使用密码模式
                .authorizedGrantTypes("password","refresh_token","authorization_code");

    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 获取密钥需要身份认证,使用单点登录时必须配置
        security.tokenKeyAccess("isAuthenticated()");
    }
}
