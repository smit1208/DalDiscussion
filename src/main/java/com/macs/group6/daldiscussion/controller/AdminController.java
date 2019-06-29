package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.service.IAdminService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    private IAdminService iAdminService;

    public AdminController(@Qualifier("AdminService")IAdminService iAdminService){
        this.iAdminService = iAdminService;
    }

    @GetMapping("/admin")
    public String admin(ModelMap model, HttpSession session) {
        String email = session.getAttribute("email").toString();
        if(email.equals("admin@dal.ca")){
            return Views.ADMIN;
        }
        else {
            return "redirect:/home";
        }
    }
}
