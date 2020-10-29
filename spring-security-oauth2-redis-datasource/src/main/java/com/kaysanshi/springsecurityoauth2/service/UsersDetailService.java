package com.kaysanshi.springsecurityoauth2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaysanshi.springsecurityoauth2.bean.Users;
import com.kaysanshi.springsecurityoauth2.dao.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Description:
 *
 * @date:2020/10/29 16:21
 * @author: kaysanshi
 **/
@Service("usersDetailService")
public class UsersDetailService implements UserDetailsService {
    /**
     * mybatis注入mapper :that could not be found
     * 一定要在启动类加入mapperScan进行扫描
     */
    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        // where email=?
        wrapper.eq("username", s);
        Users users = (Users) usersMapper.selectOne(wrapper);
        if (users == null) {
            // 數據庫中認證失敗
            throw new UsernameNotFoundException("用戶名不存在");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getUserName(), new BCryptPasswordEncoder().encode(users.getPassWord()), authorities);
    }
}
