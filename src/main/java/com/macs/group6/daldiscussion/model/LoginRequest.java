package com.macs.group6.daldiscussion.model;

public class LoginRequest {
    private String _email = "";
    private String _password = "";

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }
}
