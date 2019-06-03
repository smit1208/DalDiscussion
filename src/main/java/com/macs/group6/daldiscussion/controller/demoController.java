package com.macs.group6.daldiscussion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @RequestMapping("/")
    public String demo(){
        return "Testing works";
    }
}
