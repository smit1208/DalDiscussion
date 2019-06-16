package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.HomeDAO;
import com.macs.group6.daldiscussion.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {
    private HomeDAO homeDAO = new HomeDAO();
    private List<Post> posts = new ArrayList<>();

    @RequestMapping("/home")
    public String Home(Model model){

        posts = homeDAO.getAllPosts();
        model.addAttribute("posts",posts);
        return Views.HOMEPAGE;

        }
}
