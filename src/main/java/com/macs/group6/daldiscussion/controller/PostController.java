package com.macs.group6.daldiscussion.controller;
/**
 * @author Sharon Alva
 * Last updated: 02 August, 2019
 */

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.AmazonClient;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class PostController {
    private static final Logger logger = Logger.getLogger(PostController.class);
    private ISubscriptionService iSubscriptionService;
    private IPostService postService;

    @Autowired
    public PostController(@Qualifier("PostService") IPostService iPostService, @Qualifier("SubscriptionService")ISubscriptionService iSubscriptionService){
        this.postService = iPostService;
        this.iSubscriptionService = iSubscriptionService;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String postView(Model model, HttpSession session) {
        Map<String,Object> displaySubscriptionMap = new HashMap<>();
        int userID = (Integer)session.getAttribute("id");
        String name = (String) session.getAttribute("firstName");
        model.addAttribute("name",name);
        model.addAttribute("addPost", new Post());
        try {
            displaySubscriptionMap = iSubscriptionService.approvedSubscriptions(userID);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        List<Subscription> subscriptions = (List<Subscription>) displaySubscriptionMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        return Views.VIEWPOST;
    }


    @PostMapping(value = "/addPost")
    public String savePost(@ModelAttribute("addPost") Post post, HttpSession session) {
        int user_id = (Integer) session.getAttribute("id");
        post.setUser_id(user_id);
        List<MultipartFile> images = post.getFiles();

        //check if the input form has images
        if(images.get(0).getOriginalFilename().contains(".")){
            post.setIsImage(1);
            try{
                postService.createPostWithImage(post);
            } catch (DAOException e) {
                logger.error(e.getMessage()+"REASON: "+e.getCause().getMessage());
                return "customError";
            }

        }else{
            post.setIsImage(0);
            try {
                postService.createPost(post);
            } catch (DAOException e) {
                logger.error(e.getMessage()+"REASON: "+e.getCause().getMessage());
                return "customError";
            }
        }

        logger.info("Post added successfully");
        return "redirect:/home";
    }
}
