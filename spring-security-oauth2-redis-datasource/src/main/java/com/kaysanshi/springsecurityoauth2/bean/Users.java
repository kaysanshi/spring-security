package com.kaysanshi.springsecurityoauth2.bean;

/**
 * Description:
 *
 * @date:2020/10/29 16:31
 * @author: kaysanshi
 **/

public class Users {
    private Integer id;
    private String email;
    private String userName;
    private String passWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
