package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.impl.HomeService;
import com.macs.group6.daldiscussion.service.impl.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {
    HomeService homeService = (HomeService) ServiceFactory.getInstance().getHomeService();
    List<Post> posts = new ArrayList<>();

    @RequestMapping("/home")
    public String Home(Model model){

        posts = homeService.getAllPosts();
        model.addAttribute("posts",posts);
        return Views.HOMEPAGE;

        }
}
