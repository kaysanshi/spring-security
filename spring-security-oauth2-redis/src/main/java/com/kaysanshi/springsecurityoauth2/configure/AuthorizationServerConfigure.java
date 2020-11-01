package com.kaysanshi.springsecurityoauth2.configure;


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

    // 该对象用来支持 password 模式
    @Autowired
    AuthenticationManager authenticationManager;

    // 将令牌信息存储redis中
    @Autowired
    TokenStore redisToken;

    // 该对象将为刷新token提供支持
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 配置密码加密，因为再UserDetailsService是依赖与这个类的。
     *
     * @return
     */
    // 指定密码的加密方式
    @Bean
    PasswordEncoder passwordEncoder() {
        // 使用BCrypt强哈希函数加密方案（密钥迭代次数默认为10）
        return new BCryptPasswordEncoder();
    }

    /**
     * ClientDetailsServiceConfigurer
     * 主要是注入ClientDetailsService实例对象（唯一配置注入）。其它地方可以通过ClientDetailsServiceConfigurer调用开发配置的ClientDetailsService。
     * 系统提供的二个ClientDetailsService实现类：JdbcClientDetailsService、InMemoryClientDetailsService。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // 配置一个客户端用于password认证的。同时也可以同时配置两个，可以再配置一个基于client认证的。
        clients.inMemory()
                .withClient("password")
                .authorizedGrantTypes("password", "refresh_token") //授权模式为password和refresh_token两种
                .accessTokenValiditySeconds(1800) // 配置access_token的过期时间
                .resourceIds("rid") //配置资源id
                .scopes("all") // 允许授权范围
                .secret("$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq") //123加密后的密码
                ;
    }

    /**
     * AuthorizationServerEndpointsConfigurer 访问端点配置 是一个装载类
     * 装载Endpoints所有相关的类配置（AuthorizationServer、TokenServices、TokenStore、ClientDetailsService、UserDetailsService）。
     * tokenService用于存到redis中
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(redisToken) //配置令牌的存到redis
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    /**
     * AuthorizationServerSecurityConfigurer继承SecurityConfigurerAdapter.
     * 也就是一个 Spring Security安全配置提供给AuthorizationServer去配置AuthorizationServer的端点（/oauth/****）的安全访问规则、过滤器Filter。
     *
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 表示支持 client_id 和 client_secret 做登录认证
        // 允许使用表单认证
        security.allowFormAuthenticationForClients();
    }
}
