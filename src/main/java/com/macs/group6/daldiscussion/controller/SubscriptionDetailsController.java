package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Smit Saraiya
 */
@Controller
public class SubscriptionDetailsController {
    private static final Logger logger = Logger.getLogger(SubscriptionDetailsController.class);
    private IServiceFactory iServiceFactory;

    public SubscriptionDetailsController(){
        iServiceFactory = new ServiceFactory();
    }

    @GetMapping("/subscriptionDetails")
    public String getAllSubscriptions(ModelMap model, HttpSession session){
        int karma = (Integer) session.getAttribute("karma");
        if(karma < 1000){
            model.addAttribute("message","You need 1000 Karma points to join a group");
            logger.error("Minimum 1000 Karma points required");
            return Views.SUBSCRIPTIONDETAILS;
        }
        List<SubscriptionGroup> subscriptionGroupList = null;
        try {
            subscriptionGroupList = iServiceFactory.createSubscriptionService().getAllSubscriptions();
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        model.addAttribute("subscription",subscriptionGroupList);
        logger.info("All private groups");
        return Views.SUBSCRIPTIONDETAILS;
    }

    @RequestMapping(value = "/subscriptionDetails/{group_id}", method = RequestMethod.POST)
    public String addGroupRequest(ModelMap modelMap, HttpSession session, @PathVariable("group_id") int group_id){
        int userid = (Integer) session.getAttribute("id");
        boolean checked = false;
        List<Subscription> subscriptions = null;
        try {
            subscriptions = iServiceFactory.createSubscriptionService().fetchSubscriptionByUserID(userid);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        for (int i = 0; i <subscriptions.size() ; i++) {
           int groupId = subscriptions.get(i).getGroup_id();
           if(groupId == group_id){
               checked = true;
           }
        }
        if(checked){
            String message = "You have already requested/ registered for this group";
            modelMap.addAttribute("message",message);
            logger.error("Already registered or requested for this group");
            return Views.SUBSCRIPTIONDETAILS;
        }else {
            try {
                iServiceFactory.createSubscriptionService().addSubscriptionRequest(userid, group_id);
            } catch (DAOException e) {
                logger.error(e.getMessage());
                return "customError";
            }
            logger.info("Request send to admin for approval");
            return "redirect:/subscription";
        }
    }
}
