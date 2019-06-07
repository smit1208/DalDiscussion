package com.macs.group6.daldiscussion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class demoController {

    @GetMapping(value="/")
    public String login(){
        return "login";
    }
}
