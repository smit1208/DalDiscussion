package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IHomeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {
    private static final Logger LOGGER = LogManager.getLogger(HomepageController.class);
    Map<String,Object> postMap = new HashMap<>();

    private IHomeService homeService;

    public HomepageController(@Qualifier("HomeService") IHomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/home")
    public String Home(Model model, HttpSession session){
        String email = (String) session.getAttribute("email");
        if(email.equalsIgnoreCase("admin@dal.ca")){
            return Views.ADMIN;
        }
        postMap = homeService.getAllPosts();
        List<Post> postList = (List<Post>) postMap.get("posts");
        model.addAttribute("user",session.getAttribute("firstName"));
        model.addAttribute("posts", postList);
        LOGGER.info("Posts displayed on homepage successfully");
        return Views.HOMEPAGE;

        }
}
