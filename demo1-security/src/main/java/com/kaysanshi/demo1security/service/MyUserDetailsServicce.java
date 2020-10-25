package com.kaysanshi.demo1security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author kay三石
 * @date:2020/10/24 實現自己的UserDetailsService
 */
@Service("userDetailsService")
public class MyUserDetailsServicce implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 暫時設置一些權限
        List<GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User("kay",new BCryptPasswordEncoder().encode("123"),authorities);
    }
}
