package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IDashboardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    private IDashboardService dashboardService ;
    private static final Logger LOGGER = LogManager.getLogger(PostDetailsController.class);
    Map<String,Object> personalPostMap = new HashMap<>();


    public DashboardController(@Qualifier("DashboardService") IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @RequestMapping("/dashboard")
    public String viewDashBoard (ModelMap model, HttpSession session){
        int user_id = (Integer) session.getAttribute("id");
        personalPostMap = dashboardService.getPostsByUserID(user_id);
        List<Post> posts = (List<Post>) personalPostMap.get("personalPosts");
        model.addAttribute("posts",posts);
        return Views.DASHBOARD;
    }

    @RequestMapping("/dashboard/delete/{id}")
    public String deletePost(ModelMap model, @PathVariable("id")int post_id){
        dashboardService.deletePostById(post_id);
        LOGGER.info("Post deleted successfully");
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/dashboard/updatePost", method = RequestMethod.GET)
    public String updatePost(Model model, HttpSession session){
        LOGGER.info("Post update view");
        return Views.POSTUPDATE;
    }

    @RequestMapping(value = "/dashboard/updatePost/update", method = RequestMethod.POST)
    public String postView(@RequestParam("post_title") String postTitle,
                           @RequestParam("post_desc") String postDesc,
                           Model model, HttpSession session) {

        System.out.println("hellloowwww");
        Post post = new Post();
        int id = (Integer) session.getAttribute("id");

        if(postTitle!=null && postTitle.length()>0){
            post.setPost_title(postTitle);
        }
        if(postDesc!=null && postDesc.length()>0 ){
            post.setPost_description(postDesc);
        }

        dashboardService.updatePostById(postTitle,postDesc, id);

        return Views.POSTUPDATE;
    }
}
