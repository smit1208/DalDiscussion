package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubscriptionDetailsController {

    private ISubscriptionService iSubscriptionService;

    @Autowired
    public SubscriptionDetailsController(@Qualifier("SubscriptionService") ISubscriptionService iSubscriptionService){
        this.iSubscriptionService = iSubscriptionService;
    }

    @GetMapping("/subscriptionDetails")
    public String getAllSubscriptions(ModelMap model){
        List<SubscriptionGroup> subscriptionGroupList = iSubscriptionService.getAllSubscriptions();
        model.addAttribute("subscription",subscriptionGroupList);
        return Views.SUBSCRIPTIONDETAILS;
    }
}
