package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IDashboardService;
import com.macs.group6.daldiscussion.service.IPostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private IDashboardService dashboardService ;
    private static final Logger LOGGER = LogManager.getLogger(PostDetailsController.class);
    Map<String,Object> personalPostMap = new HashMap<>();

    @Autowired
    public DashboardController(@Qualifier("DashboardService") IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    Map<String, Object> commentMap = new HashMap<>();
    Post personalPost = new Post();

    @RequestMapping("/dashboard")
    public String viewDashBoard (ModelMap model, HttpSession session){
        int user_id = (Integer) session.getAttribute("id");
        personalPostMap = dashboardService.getPostsByUserID(user_id);
        List<Post> personalPostList = (List<Post>) personalPostMap.get("personalPosts");
        model.addAttribute("user",user_id);
        model.addAttribute("postlist", personalPostList);
        LOGGER.info("Posts on Dashboard page displayed successfully");

        return Views.DASHBOARD;
    }

    @RequestMapping("/dashboard/{id}")
    public String deletePost(ModelMap model, HttpSession session, @PathVariable("id")int post_id){
        System.out.println(post_id);
        dashboardService.deletePostById(post_id);
        LOGGER.info("Post deleted successfully");
        return "redirect:/dashboard";
    }
}
