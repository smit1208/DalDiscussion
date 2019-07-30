package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.ReportedPost;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {
    private static final Logger logger = Logger.getLogger(HomepageController.class);

    private IServiceFactory iServiceFactory;

    public HomepageController() {
        iServiceFactory = new ServiceFactory();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Home(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        int user_id = (Integer) session.getAttribute("id");
        if (email.equalsIgnoreCase("admin@dal.ca")) {
            return Views.ADMIN;
        }
        int group_id = 5;
        model.addAttribute("user", session.getAttribute("firstName"));
        List<Post> postList = null;
        try {
            postList = iServiceFactory.createHomeService().getPostsByGroupId(group_id);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        List<Post> copyPostList = new ArrayList<Post>();
        List<ReportedPost> reportedPosts = null;
        try {
            reportedPosts = iServiceFactory.createHomeService().fetchReportedPostByUserId(user_id);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
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
        try {
            iServiceFactory.createHomeService().addReportingPost(user_id, post_id);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String search(@RequestParam("search") String search, Model model) {

        String searchString = search.replaceAll("[^\\dA-Za-z ]", "");
        List<Post> searchedPost = null;
        try {
            searchedPost = iServiceFactory.createHomeService().getSearchedPost(searchString);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        String message = "";
        if (searchedPost.size() == 0) {
            message = "No Result Found";
            model.addAttribute("error", message);
        }
        model.addAttribute("posts", searchedPost);
        return Views.HOMEPAGE;
    }
}
