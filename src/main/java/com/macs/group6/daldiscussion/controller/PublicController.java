package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.ResetPasswordRequest;
import com.macs.group6.daldiscussion.model.ResetPasswordResponse;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailRequest;
import com.macs.group6.daldiscussion.model.SendForgotPasswordEmailResponse;
import com.macs.group6.daldiscussion.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PublicController {

    private static final Logger LOGGER = LogManager.getLogger(PublicController.class);
    private IUserService iUserService;

    private IObserver iUserObserver;
    private ISubject ipostService;
    public PublicController(@Qualifier("PostService") ISubject iPostService){
        this.ipostService = iPostService;
        this.iUserService = new UserService(new UserDAO());
    }

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
        resetPasswordRequest.setPassword(password);
        resetPasswordRequest.setPasswordRetype(passwordRetype);
        resetPasswordRequest.setToken(token);
        ResetPasswordResponse resetPasswordResponse = ResetPasswordService.getInstance().run(resetPasswordRequest);
        if (resetPasswordResponse.getIsError()) {
            message = resetPasswordResponse.getErrorMessage();
        } else {
            message = "Password has been changed!";
        }
        model.addAttribute("message", message);
        LOGGER.info("Password reset success");
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
        sendForgotPasswordEmailRequest.setEmail(email);
        SendForgotPasswordEmailResponse sendForgotPasswordEmailResponse = SendForgotPasswordEmailService.getInstance().run(sendForgotPasswordEmailRequest);
        if (sendForgotPasswordEmailResponse.getIsError()) {
            message = sendForgotPasswordEmailResponse.getErrorMessage();
        } else {
            LOGGER.info("Password link successfully sent");
            message = "Instruction email has been sent to your mailbox.";
        }
        model.addAttribute("message", message);
        return "forgot-password";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session) {
        fillCommonModel(model, session);
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        model.addAttribute("message", "");
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpSession session, HttpServletRequest request,
                            @RequestParam(name = "username") String email, @RequestParam(name = "password") String password) {
        fillCommonModel(model, session);

        model.addAttribute("email", email);
        model.addAttribute("password", password);
        List<User> users = iUserService.getUserByEmail(email);
        String message = "";

        try {
            request.login(email, password);
            session.setAttribute("email",email);
            session.setAttribute("firstName",users.get(0).getFirstName());
            session.setAttribute("karma",users.get(0).getKarmaPoints());
            session.setAttribute("id",users.get(0).getId());
           // UserServiceObserver obs1 = (UserServiceObserver) UserServiceObserver.getInstance();
            this.iUserObserver = UserServiceObserver.getInstance(ipostService);
            return "redirect:/";
        } catch (Exception e) {
            LOGGER.error("Error in log in");
            message = e.getMessage();
        }
        LOGGER.info("Successfully logged in");
        model.addAttribute("message", message);
        return "login";
    }

    private void fillCommonModel(Model model, HttpSession session) {
        boolean online = false;
        int userid = 0;
        User authUser = new User();
        try {
            if ("true".equals(session.getAttribute("online"))) {
                online = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (session.getAttribute("userid") != null) {
                userid = Integer.parseInt(session.getAttribute("userid") + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (userid > 0) {
                authUser = UserDAO.getInstance().findById(userid);
            }
            if (authUser == null) {
                authUser = new User();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("online", online);
        model.addAttribute("userid", userid);
        model.addAttribute("authUser", authUser);
    }
}
