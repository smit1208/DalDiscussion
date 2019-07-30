package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.IUserService;
import com.macs.group6.daldiscussion.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
@Controller
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);
    private IUserService iUserService = UserService.getInstance();
    @Autowired
    private JavaMailSender javaMailSender;
    private IServiceFactory iServiceFactory;

    public AdminController(){
        iServiceFactory = new ServiceFactory();
    }

    @RequestMapping(value = "/admin/allRequests", method = RequestMethod.GET)
    public String fetchAllSubscriptionRequests(Model model) {
        List<Subscription> subscriptions = null;
        try {
            subscriptions = iServiceFactory.createAdminService().fetchAllSubscriptionRequests();
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        model.addAttribute("subscriptions", subscriptions);
        logger.info("Pending requests list fetched");
        return Views.PENDINGREQUESTADMIN;
    }

    @RequestMapping(value = "/admin/allRequests/{id}", method = RequestMethod.POST)
    public String approveRequest(@PathVariable("id") int subscription_id) {
        Subscription subscription = null;
        try {
            subscription = iServiceFactory.createSubscriptionService().fetchSubscriptionByID(subscription_id);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        int user_id = subscription.getUser_id();
        User user = iUserService.getUserById(user_id);
        String email = user.getEmail();
        try {
            iServiceFactory.createAdminService().approveSubscription(subscription_id);
        } catch (DAOException e) {
            logger.error(e.getMessage());

            return "customError";
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Approval to your Subscription request");
        simpleMailMessage.setText("Your Subscription request has been approved ");
        javaMailSender.send(simpleMailMessage);
        logger.info("Request approved by admin, Email send");
        return "redirect:/admin/allRequests";
    }

    @RequestMapping(value = "/admin/reported", method = RequestMethod.GET)
    public String getPostsByMaxReports(Model model) {
        Map<String, Object> reportedMap = new HashMap<>();
        try {
            reportedMap = iServiceFactory.createAdminService().getPostsByMaxReports();
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        List<Post> posts = (List<Post>) reportedMap.get("post");
        List<User> users = (List<User>) reportedMap.get("user");
        model.addAttribute("userList", users);
        model.addAttribute("postList", posts);
        return Views.ADMINREPORT;
    }
    @RequestMapping(value = "/admin/reported/{id}/{title}", method = RequestMethod.GET)
    public String SendReportEmail(@PathVariable("id")int user_id, @PathVariable("title") String title) {
        User user = iUserService.getUserById(user_id);
        String email = user.getEmail();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Reported Post");
        simpleMailMessage.setText("Your Post " +title+ " has been reported by many customers, please delete it! ");
        javaMailSender.send(simpleMailMessage);
        logger.info(" Email send");
        return "redirect:/admin/reported";
    }
}
