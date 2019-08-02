package com.macs.group6.daldiscussion.model;
/**
 * 
 *
 * @author Kush Rao
 */
public class ResetPasswordRequest {
    private String _token = "";
    private String _password = "";
    private String _passwordRetype = "";

    public String getToken() {
        return _token;
    }

    public void setToken(String token) {
        _token = token;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getPasswordRetype() {
        return _passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        _passwordRetype = passwordRetype;
    }
}
