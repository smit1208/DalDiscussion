package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private IRegisterService registerService;

    @Autowired
    public RegisterController(@Qualifier("RegisterService") IRegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("title", "User Registration");
        return Views.REGISTER;
    }

    @PostMapping(value = "/register")
    public String register(User userReg,
                           @RequestParam("fname") String fname,
                           @RequestParam("lname") String lname,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password, Model model) {
        String message = "";

        userReg.setFirstName(fname);
        userReg.setLastName(lname);
        userReg.setEmail(email);
        userReg.setPassword(password);
        if(registerService.userExists(userReg)){
            logger.error("User already exists");
            message = "Email already present! Register with another email id";
            model.addAttribute("message",message);
            return Views.REGISTER;
        }
        else {
            registerService.create(userReg);
        }
        logger.info("User successfully created");
        return "redirect:/login";
    }

}
