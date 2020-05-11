package com.auth;

public class UserInfo {
    String username;
    String email;
    String password;
    String phone;
    String age;

    public UserInfo() {
    }

    public UserInfo(String username, String email, String password,String phone,String age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone=phone;
        this.age=age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
