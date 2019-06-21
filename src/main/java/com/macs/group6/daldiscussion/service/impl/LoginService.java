package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.dao.impl.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.LoginRequest;
import com.macs.group6.daldiscussion.model.LoginResponse;

import java.util.List;

public class LoginService {
    private static LoginService __instance;

    public static LoginService instance() {
        if (__instance == null) {
            __instance = new LoginService();
        }
        return __instance;
    }

    public LoginResponse run(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            if (request.username.trim().length() == 0) {
                response.setError("Login.UsernameRequired", "Username is required!");
                return response;
            }
            if (request.password.trim().length() == 0) {
                response.setError("Login.PasswordRequired", "Password is required!");
                return response;
            }
            List<User> userList = UserDAO.instance().findByUsername(request.username);
            if (userList.size() == 0) {
                response.setError("Login.UserNotFound", "User is not found!");
                return response;
            }
            if (userList.size() != 1) {
                response.setError("Login.UserDuplicated", "User is duplicated!");
                return response;
            }
            User user = userList.get(0);
            if (!user.password.equals(request.password)) {
                response.setError("Login.PasswordNotMatch", "Password is not match!");
                return response;
            }
            response.usercode = user.code;
        } catch (Exception e) {
            response.setError("Login.Failed", e);
        }
        return response;
    }
}
