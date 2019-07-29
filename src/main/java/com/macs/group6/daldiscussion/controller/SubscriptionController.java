package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Subscription;
import org.apache.log4j.Logger;
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
    private IServiceFactory iServiceFactory;

    public SubscriptionController(){
        iServiceFactory = new ServiceFactory();
    }
    @RequestMapping("/subscription")
    public String Subscription(Model model, HttpSession session){
        Map<String,Object> displaySubMap = new HashMap<>();
        int userID = (Integer)session.getAttribute("id");
        displaySubMap = iServiceFactory.createSubscriptionService().approvedSubscriptions(userID);
        List<Subscription> subscriptions = (List<Subscription>) displaySubMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        List<Subscription> subscriptionGroupList = iServiceFactory.createSubscriptionService().fetchSubscriptionByUserID(userID);
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
