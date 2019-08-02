package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.exceptions.DAOException;
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

/**
 * @author Smit Saraiya
 */
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
        try {
            displaySubMap = iServiceFactory.createSubscriptionService().approvedSubscriptions(userID);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        List<Subscription> subscriptions = (List<Subscription>) displaySubMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        List<Subscription> subscriptionGroupList = null;
        try {
            subscriptionGroupList = iServiceFactory.createSubscriptionService().fetchSubscriptionByUserID(userID);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
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
