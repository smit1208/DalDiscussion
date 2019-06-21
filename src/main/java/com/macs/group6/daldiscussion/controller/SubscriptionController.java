package com.macs.group6.daldiscussion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SubscriptionController {

    @RequestMapping("/subscription")
    public String Subscription(){

        return Views.SUBSCRIPTION;
    }
}
