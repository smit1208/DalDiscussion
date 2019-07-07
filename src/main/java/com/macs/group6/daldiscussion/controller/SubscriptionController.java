package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SubscriptionController {
    private ISubscriptionService iSubscriptionService;
    Map<String,Object> displaySubMap = new HashMap<>();

    public SubscriptionController(@Qualifier("SubscriptionService")ISubscriptionService iSubscriptionService){
        this.iSubscriptionService = iSubscriptionService;
    }
    @RequestMapping("/subscription")
    public String Subscription(Model model, HttpSession session){
        int userID = (Integer)session.getAttribute("id");
        displaySubMap = iSubscriptionService.approvedSubscriptions(userID);
        List<Subscription> subscriptions = (List<Subscription>) displaySubMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        return Views.SUBSCRIPTION;
    }
}
