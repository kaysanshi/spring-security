package com.kaysanshi.demo4security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaysanshi.demo4security.bean.Users;
import com.kaysanshi.demo4security.dao.UsersMapper;
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
 * @Author kay三石
 * @date:2020/10/24
 * 通过注入mapper然后在数据库中进行查询出响应的，然后作授权
 */
@Service("userDetailsServiceByDB")
public class UserDetailsServiceByDB implements UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 調用userMapper 根據用戶名查數據庫
        QueryWrapper <Users> wrapper = new QueryWrapper <>();
        // where email=?
        wrapper.eq("email", s);
        Users users = (Users) usersMapper.selectOne(wrapper);
        if (users == null) {
            // 數據庫中認證失敗
            throw new UsernameNotFoundException("用戶名不存在");
        }
        List<GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getEmail(),new BCryptPasswordEncoder().encode(users.getPassword()),authorities);
    }
}
