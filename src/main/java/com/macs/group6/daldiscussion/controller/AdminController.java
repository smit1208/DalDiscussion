package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.IAdminService;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import com.macs.group6.daldiscussion.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
// https://www.mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
@Controller
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);
    private IAdminService iAdminService;
    private IUserService iUserService;
    private ISubscriptionService iSubscriptionService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public AdminController(@Qualifier("AdminService")IAdminService iAdminService,
                           @Qualifier("UserService")IUserService iUserService,
                           @Qualifier("SubscriptionService") ISubscriptionService iSubscriptionService){
        this.iAdminService = iAdminService;
        this.iUserService = iUserService;
        this.iSubscriptionService = iSubscriptionService;
    }

    @RequestMapping(value = "/admin/allRequests", method = RequestMethod.GET)
    public String fetchAllSubscriptionRequests(Model model){
        List<Subscription> subscriptions = iAdminService.fetchAllSubscriptionRequests();
        model.addAttribute("subscriptions",subscriptions);
        logger.info("Pending requests list fetched");
        return Views.PENDINGREQUESTADMIN;
    }

    @RequestMapping(value = "/admin/allRequests/{id}", method = RequestMethod.POST)
    public String approveRequest(@PathVariable("id") int subscription_id){
        Subscription subscription = iSubscriptionService.fetchSubscriptionByID(subscription_id);
        int user_id = subscription.getUser_id();
        User user = iUserService.getUserById(user_id);
        String email = user.getEmail();
        iAdminService.approveSubscription(subscription_id);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Approval to your Subscription request");
        simpleMailMessage.setText("Your Subscription request has been approved ");
        javaMailSender.send(simpleMailMessage);
        logger.info("Request approved by admin, Email send");
        return "redirect:/admin/allRequests";
    }
}
