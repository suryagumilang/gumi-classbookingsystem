package com.example.surya.cbsv2;

/**
 * Created by Surya on 09/01/2017.
 */
public class CBSv2_LoginController {

    String username, password;
    boolean isLogin;

    public CBSv2_LoginController(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public boolean doLogin(){
        if(getUsername() == "admin" && getPassword() == "admin"){
            isLogin = true;
        }else {
            isLogin = false;
        }
        return isLogin;
    }
}
