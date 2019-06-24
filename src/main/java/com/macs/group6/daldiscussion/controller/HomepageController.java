package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.HomeService;
import com.macs.group6.daldiscussion.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {
    private static final Logger LOGGER = LogManager.getLogger(HomepageController.class);
    HomeService homeService = (HomeService) ServiceFactory.getInstance().getHomeService((IHomeDAO) DAOFactory.getInstance().getHomeDAO());
    Map<String,Object> postMap = new HashMap<>();

    @RequestMapping("/home")
    public String Home(Model model){

        postMap = homeService.getAllPosts();
        List<Post> postList = (List<Post>) postMap.get("posts");
        model.addAttribute("posts", postList);
        LOGGER.info("Posts displayed on homepage successfully");
        return Views.HOMEPAGE;

        }
}
