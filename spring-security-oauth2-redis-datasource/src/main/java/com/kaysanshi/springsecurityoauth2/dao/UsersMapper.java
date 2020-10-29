package com.kaysanshi.springsecurityoauth2.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaysanshi.springsecurityoauth2.bean.Users;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @date:2020/10/29 16:32
 * @author: kaysanshi
 **/
@Repository
public interface UsersMapper  extends BaseMapper<Users> {
}
