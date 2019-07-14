package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IDashboardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    Map<String,Object> PostMap = new HashMap<>();

    @Autowired
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
        System.out.println(post_id);
        dashboardService.deletePostById(post_id);
        LOGGER.info("Post deleted successfully");
        return "redirect:/dashboard";
    }

//    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
//    public String updatePost(Model model, HttpSession session){
//        LOGGER.info("Post update view");
//        return Views.POSTUPDATE;
//    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String postView(Model model, HttpSession session, @PathVariable("id") int id) {

       return "redirect:/dashboard";
    }

    @RequestMapping(value = "/updatedData/{id}", method = RequestMethod.POST)
    public String UpdatedPost(Model model, HttpSession session, @RequestParam("title") String post_title,
                              @RequestParam("desc") String post_desc, @PathVariable("id") int id) {
        dashboardService.updatePostById(post_title,post_desc,id);
        System.out.println(post_title);
        return "redirect:/dashboard";
    }
}
