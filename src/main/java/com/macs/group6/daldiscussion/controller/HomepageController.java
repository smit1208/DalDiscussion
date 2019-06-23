package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.HomeService;
import com.macs.group6.daldiscussion.service.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {
    HomeService homeService = (HomeService) ServiceFactory.getInstance().getHomeService();
    Map<String,Object> postMap = new HashMap<>();

    @RequestMapping("/home")
    public String Home(Model model){

        postMap = homeService.getAllPosts();
        List<Post> postList = (List<Post>) postMap.get("posts");
        model.addAttribute("posts", postList);
        return Views.HOMEPAGE;

        }
}
