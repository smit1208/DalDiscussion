package com.macs.group6.daldiscussion.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// https://www.javadevjournal.com/spring-boot/spring-boot-whitelabel-error-page/
@Controller
public class CustomErrorController implements ErrorController {
    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }
    @RequestMapping(value = PATH)
    public String error() {
        return "customError";
    }
}
