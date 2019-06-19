package com.macs.group6.daldiscussion.model;

public class LoginRequest {
    private String _username = "";
    private String _password = "";

    public String getUsername() {
        return _username;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }
}
