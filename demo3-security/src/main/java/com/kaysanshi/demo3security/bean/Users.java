package com.kaysanshi.demo3security.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @Author kay三石
 * @date:2020/10/24
 */
@Data
@TableName("user")
public class Users {
    private Integer id;
    private String email;
    private String password;
}
