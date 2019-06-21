package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.ResetPasswordRequest;
import com.macs.group6.daldiscussion.model.ResetPasswordResponse;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailRequest;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailResponse;
import com.macs.group6.daldiscussion.service.ResetPasswordService;
import com.macs.group6.daldiscussion.service.SendForgotPasswordEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublicController {
    @GetMapping("/")
    public String getIndex() {
        return "redirect:/home";
    }

    @GetMapping("/prelogin")
    public String getPreLogin(HttpServletRequest request) {
        try {
            if (request.getParameterMap().containsKey("logout")) {
                String goUrl = "/";
                try {
                    request.logout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "redirect:" + goUrl;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/reset-password")
    public String getResetPassword(
            Model model,
            HttpSession session,
            @RequestParam(name = "token") String token
    ) {
        fillCommonModel(model, session);
        model.addAttribute("password", "");
        model.addAttribute("passwordRetype", "");
        model.addAttribute("token", token);
        model.addAttribute("message", "");
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(
            Model model,
            HttpSession session,
            @RequestParam(name = "token") String token,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "passwordRetype") String passwordRetype
    ) {
        fillCommonModel(model, session);
        model.addAttribute("password", password);
        model.addAttribute("passwordRetype", passwordRetype);
        model.addAttribute("token", token);
        String message = "";

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.password = password;
        resetPasswordRequest.passwordRetype = passwordRetype;
        resetPasswordRequest.token = token;
        ResetPasswordResponse resetPasswordResponse = ResetPasswordService.instance().run(resetPasswordRequest);
        if (resetPasswordResponse.isError) {
            message = resetPasswordResponse.errorMessage;
        } else {
            message = "Password has been changed!";
        }
        model.addAttribute("message", message);
        return "reset-password";
    }

    @GetMapping("/forgot-password")
    public String getForgotPassword(Model model, HttpSession session) {
        fillCommonModel(model, session);
        model.addAttribute("email", "");
        model.addAttribute("message", "");
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String postForgotPassword(Model model, HttpSession session, HttpServletRequest request,
                            @RequestParam(name = "email") String email) {
        fillCommonModel(model, session);
        model.addAttribute("email", email);
        String message = "";

        SendForgotPasswordEmailRequest sendForgotPasswordEmailRequest = new SendForgotPasswordEmailRequest();
        sendForgotPasswordEmailRequest.email = email;
        SendForgotPasswordEmailResponse sendForgotPasswordEmailResponse = SendForgotPasswordEmailService.instance().run(sendForgotPasswordEmailRequest);
        if (sendForgotPasswordEmailResponse.isError) {
            message = sendForgotPasswordEmailResponse.errorMessage;
        } else {
            message = "Instruction email has been sent to your mailbox.";
        }
        model.addAttribute("message", message);
        return "forgot-password";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session) {
        fillCommonModel(model, session);
        model.addAttribute("username", "");
        model.addAttribute("password", "");
        model.addAttribute("message", "");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpSession session, HttpServletRequest request,
                            @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        fillCommonModel(model, session);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        String message = "";

        try {
            request.login(username, password);
            return "redirect:/";
        } catch (Exception e) {
            message = e.getMessage();
        }
        model.addAttribute("message", message);
        return "login";
    }

    private void fillCommonModel(Model model, HttpSession session) {
        boolean online = false;
        String usercode = "";
        User authUser = new User();
        try {
            if ("true".equals(session.getAttribute("online"))) {
                online = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (session.getAttribute("usercode") != null) {
                usercode = session.getAttribute("usercode") + "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (usercode.length() > 0) {
                authUser = UserDAO.instance().findByCode(usercode);
            }
            if (authUser == null) {
                authUser = new User();
                authUser.code = usercode;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("online", online);
        model.addAttribute("usercode", usercode);
        model.addAttribute("authUser", authUser);
    }
}
