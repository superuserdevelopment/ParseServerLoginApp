package com.superuser.loginparseserver;

public class UserModels {
    protected String username;
    private String password;
    protected String email;
    //Password cannot be retrieved but can only be set
    void setPassword(String pass){
        password = pass;
    }

    UserModels(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
