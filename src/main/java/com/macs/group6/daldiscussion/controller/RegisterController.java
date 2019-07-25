package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.Validator.RegistrationValidator;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.service.IRegisterService;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private IRegisterService registerService;
    private RegistrationValidator registrationValidator = RegistrationValidator.getInstance();

    @Autowired
    public RegisterController(@Qualifier("RegisterService") IRegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("registrationForm", new User());
        return Views.REGISTER;
    }

    @PostMapping(value = "/register")
    public String register(
                           @ModelAttribute("registrationForm") User userReg,
                           BindingResult bindingResult,
                           Model model) {

        //userReg.set_confirmPassword();
        registrationValidator.validate(userReg, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.info("Error occurred during validation");
            return Views.REGISTER;
        } else {
            String message = "";


            if (registerService.userExists(userReg)) {
                logger.error("User already exists");
                model.addAttribute("errorMessage","User already exists");
                message = "Email already present! Register with another email id";
                model.addAttribute("message", message);
                return Views.REGISTER;
            }

            else {
                registerService.create(userReg);
            }
            logger.info("User registered successfully!");
        }
        return "redirect:/login";
    }

}
