package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.UserRegister;
import com.macs.group6.daldiscussion.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @Controller
    public class RegisterController {

   Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Map<String, Object> map) {
        map.put("title", "User Registration");
        return "/registerForm";
    }

    @PostMapping(value = "/register")
    public String register(UserRegister userReg,
                           @RequestParam("fname") String fname,
                           @RequestParam("lname") String lname,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password, Map<String, Object> map) {
        map.put("fname", fname);
        map.put("lname", lname);
        map.put("email", email);
        map.put("password", password);
        userReg.setFname(fname);
        userReg.setLname(lname);
        userReg.setEmail(email);
        userReg.setPassword(password);
        RegisterService callService = new RegisterService();
        callService.create(userReg);
        return Views.REGISTER;
    }

    }
