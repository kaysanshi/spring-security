package com.kaysanshi.springsecurityoauth2.service;

import com.kaysanshi.springsecurityoauth2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author kay三石
 * @date:2020/10/31
 */
@Service
public class UserService  implements UserDetailsService {

    /**
     * 注入刚引入的bean
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String password =passwordEncoder.encode("123");
        return new User("admin",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
