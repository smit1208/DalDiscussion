package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.dao.VerificationDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.entities.Verification;
import com.macs.group6.daldiscussion.model.*;

public class ResetPasswordService {
    private static ResetPasswordService __instance;

    public static ResetPasswordService instance() {
        if (__instance == null) {
            __instance = new ResetPasswordService();
        }
        return __instance;
    }

    public ResetPasswordResponse run(ResetPasswordRequest request) {
        ResetPasswordResponse response = new ResetPasswordResponse();
        try {
            if (request.password.trim().length() == 0) {
                response.setError("ResetPassword.PasswordRequired", "Password is required!");
                return response;
            }
            if (!request.password.equals(request.passwordRetype)) {
                response.setError("ResetPassword.RetypedPasswordNotMatch", "Retyped password does not match!");
                return response;
            }
            if (request.token.trim().length() == 0) {
                response.setError("ResetPassword.TokenRequired", "Token is required!");
                return response;
            }
            Verification verification = VerificationDAO.instance().findByVerifyToken(request.token);
            if (verification == null) {
                response.setError("ResetPassword.TokenNotFound", "Token is not found!");
                return response;
            }
            User user = UserDAO.instance().findByCode(verification.tokenCode);
            if (user == null) {
                response.setError("ResetPassword.UserNotFound", "User which is associated to token is not found!");
                return response;
            }
            user.password = request.password;
            UserDAO.instance().save(user);
            VerificationDAO.instance().delete(verification.code);
        } catch (Exception e) {
            response.setError("ResetPassword.Failed", e);
        }
        return response;
    }
}
