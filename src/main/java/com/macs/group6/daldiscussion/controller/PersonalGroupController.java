package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.IServiceFactory;
import com.macs.group6.daldiscussion.factory.ServiceFactory;
import com.macs.group6.daldiscussion.model.Post;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonalGroupController {
    private static final Logger logger = Logger.getLogger(PersonalGroupController.class);
    private IServiceFactory iServiceFactory;

    public PersonalGroupController(){
       iServiceFactory = new ServiceFactory();
    }
    @RequestMapping(value = "/subscriptionDetails/{id}", method = RequestMethod.GET)
    public String getAllPersonalPosts(Model model, @PathVariable("id") int groupId){
        Map<String,Object> privatePostMap = new HashMap<>();
        try {
            privatePostMap = iServiceFactory.createPersonalGroupService().getPrivatePostsByGroupID(groupId);
        } catch (DAOException e) {
            logger.error(e.getMessage());
            return "customError";
        }
        switch (groupId){
            case 1:
                model.addAttribute("QA","Quality Assurance");
                break;
            case 2:
                model.addAttribute("Web","Web Development");
                break;
            case 3:
                model.addAttribute("Cloud","Cloud Computing");
                break;
            case 4:
                model.addAttribute("Data","Data Science");
                break;
            case 5:
                return "redirect:/home";
        }

        List<Post> posts = (List<Post>) privatePostMap.get("privatePosts");
        model.addAttribute("privatePosts",posts);
        logger.info("Private Posts rendered successfully");
        return Views.PERSONALGROUPS;
    }
}
