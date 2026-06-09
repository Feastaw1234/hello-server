package com.stu212306158.helloserver.dto;

public class UserDTO {
    // 前端传递：用户名
    private String username;
    // 前端传递：密码
    private String password;

    // Getter & Setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}