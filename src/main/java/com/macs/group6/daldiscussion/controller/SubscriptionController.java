package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.apache.log4j.Logger;
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
    private static final Logger logger = Logger.getLogger(SubscriptionController.class);
    private ISubscriptionService iSubscriptionService;

    public SubscriptionController(@Qualifier("SubscriptionService")ISubscriptionService iSubscriptionService){
        this.iSubscriptionService = iSubscriptionService;
    }
    @RequestMapping("/subscription")
    public String Subscription(Model model, HttpSession session){
        Map<String,Object> displaySubMap = new HashMap<>();
        int userID = (Integer)session.getAttribute("id");
        displaySubMap = iSubscriptionService.approvedSubscriptions(userID);
        List<Subscription> subscriptions = (List<Subscription>) displaySubMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        List<Subscription> subscriptionGroupList = iSubscriptionService.fetchSubscriptionByUserID(userID);
        String message="";
        if(subscriptionGroupList.size() == 4){
            message = "max subscriptions reached";
            model.addAttribute("error",message);
            return Views.SUBSCRIPTION;
        }else{
            model.addAttribute("subList",subscriptionGroupList);
        }

        logger.info("Subscription list fetched successfully");
        return Views.SUBSCRIPTION;
    }
}
