package com.kaysanshi.demo4security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author kay三石
 * @date:2020/10/24
 */
@Configuration
public class SecurityConfigByDB extends WebSecurityConfigurerAdapter {
    //
    @Autowired
    private UserDetailsService userDetailsServiceByDB;

    // 注入数据源对象
    @Autowired
    private DataSource dataSource;

    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 这里可以自己进行创建表
        // jdbcTokenRepository.setCreateTableOnStartup();
        return jdbcTokenRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证管理器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceByDB).passwordEncoder(passwordEncoder());
    }

    /**
     * configure(HttpSecurity)方法定义了哪些URL路径应该被保护，哪些不应该。具体来说，“/”和“/ home”路径被配置为不需要任何身份验证。所有其他路径必须经过身份验证。
     * 用来配置 HttpSecurity 。 HttpSecurity 用于构建一个安全过滤器链 SecurityFilterChain 。SecurityFilterChain 最终被注入核心过滤器
     * HttpSecurity的使用：
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//开启登录配置
                // 设置哪些访问路径不需要访问权限，可以直接访问
                .antMatchers("/resources/**", "/signup", "/about", "/home").permitAll()
                // 设置user对象的权限 只能设置一个权限
                .antMatchers("/test/index").hasAuthority("admin")
                // 当用户权限有其中一个就可以
                .antMatchers("/hello").hasAnyAuthority("admins,manager")
                // 必须有这个role 的，才可以访问 hasRole("sale")==>ROLE_sale
                //如果配置的hasRole("sale1") AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale"); 为这个是则访问失败403
                // .antMatchers("/hello").hasRole("sale")
                // 配置多个
                // .antMatchers("/hello").hasAnyRole("sale1,admin")
                .anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
                .and()
                .formLogin()
                //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login") // 登录访问路径
                .defaultSuccessUrl("/success.html").permitAll()
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60) //设置有效时长以秒为单位
                .userDetailsService(userDetailsServiceByDB)
                .and()
                .csrf().disable(); // 关闭CSrf的配置，但是默认是开启的CERF
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        // 跳转到log
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/log");
    }

}
