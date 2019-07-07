package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.LoginRequest;
import com.macs.group6.daldiscussion.model.LoginResponse;

import java.util.List;

public class LoginService {
    private static LoginService __instance;
    public static LoginService getInstance() {
        if (__instance == null) {
            __instance = new LoginService();
        }
        return __instance;
    }

    public LoginResponse run(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            if (request.getEmail().trim().length() == 0) {
                response.setError("Login.EmailRequired", "Email is required!");
                return response;
            }
            if (request.getPassword().trim().length() == 0) {
                response.setError("Login.PasswordRequired", "Password is required!");
                return response;
            }
            List<User> userList = UserDAO.getInstance().findByEmail(request.getEmail());
            if (userList.size() == 0) {
                response.setError("Login.UserNotFound", "User is not found!");
                return response;
            }
            if (userList.size() != 1) {
                response.setError("Login.UserDuplicated", "User is duplicated!");
                return response;
            }
            User user = userList.get(0);
            if (!user.getPassword().equals(request.getPassword())) {
                response.setError("Login.PasswordNotMatch", "Password is not match!");
                return response;
            }
            response.setUserId(user.getId());
        } catch (Exception e) {
            response.setError("Login.Failed", e);
        }
        return response;
    }
}
