package com.any.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Login {
    private String login;
    @JSONField(serialize = false)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}