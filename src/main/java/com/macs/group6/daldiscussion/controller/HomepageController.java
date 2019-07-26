package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import com.macs.group6.daldiscussion.service.IHomeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {
    private static final Logger logger = Logger.getLogger(HomepageController.class);

    private IHomeService homeService;

    @Autowired
    public HomepageController(@Qualifier("HomeService") IHomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(Model model, HttpSession session) {
        Map<String, Object> postMap = new HashMap<>();
        String email = (String) session.getAttribute("email");
        int user_id = (Integer) session.getAttribute("id");
        if (email.equalsIgnoreCase("admin@dal.ca")) {
            return Views.ADMIN;
        }
        postMap = homeService.getAllPosts();
        model.addAttribute("user", session.getAttribute("firstName"));
        List<Post> postList = (List<Post>) postMap.get("posts");
        List<Post> copyPostList = new ArrayList<Post>();
        List<ReportedPost> reportedPosts = homeService.fetchReportedPostByUserId(user_id);
        boolean check = false;
        for (int i = 0; i < postList.size(); i++) {
            if (reportedPosts.size() > 0) {
                for (int j = 0; j < reportedPosts.size(); j++) {
                    if (postList.get(i).getId() == reportedPosts.get(j).getPost_id()) {
                        check = true;
                    }
                }
                if (!check) {
                    copyPostList.add(postList.get(i));
                }
            } else {
                copyPostList = postList;
            }
            check = false;
        }
        model.addAttribute("posts", copyPostList);
        logger.info("Posts displayed on homepage successfully");
        return Views.HOMEPAGE;
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.POST)
    public String Report(HttpSession session, @PathVariable("id") int post_id) {
        int user_id = (Integer) session.getAttribute("id");
        homeService.addReportingPost(user_id, post_id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search, Model model) {

        String searchString = search.replaceAll("[^\\dA-Za-z ]", "");
        List<Post> searchedPost = homeService.getSearchedPost(searchString);
        String message = "";
        if (searchedPost.size() == 0) {
            message = "No Result Found";
            model.addAttribute("error", message);
        }
        model.addAttribute("posts", searchedPost);
        return Views.HOMEPAGE;
    }
}
