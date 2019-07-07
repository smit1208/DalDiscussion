package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SubscriptionDetailsController {

    private ISubscriptionService iSubscriptionService;


    @Autowired
    public SubscriptionDetailsController(@Qualifier("SubscriptionService") ISubscriptionService iSubscriptionService){
        this.iSubscriptionService = iSubscriptionService;
    }

    @GetMapping("/subscriptionDetails")
    public String getAllSubscriptions(ModelMap model, HttpSession session){
        int karma = (Integer) session.getAttribute("karma");
        if(karma != 100){
            model.addAttribute("message","You need 100 Karma points to join a group");
            return Views.SUBSCRIPTION;
        }
        List<SubscriptionGroup> subscriptionGroupList = iSubscriptionService.getAllSubscriptions();
        model.addAttribute("subscription",subscriptionGroupList);
        return Views.SUBSCRIPTIONDETAILS;
    }

    @RequestMapping(value = "/subscriptionDetails/{group_id}", method = RequestMethod.POST)
    public String addGroupRequest(ModelMap modelMap, HttpSession session, @PathVariable("group_id") int group_id){
        int userid = (Integer) session.getAttribute("id");
        boolean checked = false;
        List<Subscription> subscriptions = iSubscriptionService.fetchSubscriptionByUserID(userid);
        for (int i = 0; i <subscriptions.size() ; i++) {
            System.out.println(subscriptions.size());
           int groupId = subscriptions.get(i).getGroup_id();
           if(groupId == group_id){
               checked = true;
           }
        }
        if(checked){
            String message = "You have already requested/ registered for this group";
            modelMap.addAttribute("message",message);
            return Views.SUBSCRIPTIONDETAILS;
        }else {
            iSubscriptionService.addSubscriptionRequest(userid, group_id);
            return Views.SUBSCRIPTION;
        }
    }
}
