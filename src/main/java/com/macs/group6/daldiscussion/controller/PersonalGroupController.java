package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IPersonalGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private IPersonalGroupService iPersonalGroupService;
    @Autowired
    public PersonalGroupController(@Qualifier("PersonalGroupService")IPersonalGroupService iPersonalGroupService){
        this.iPersonalGroupService = iPersonalGroupService;
    }
    Map<String,Object> privatePostMap = new HashMap<>();
    @RequestMapping(value = "/subscriptionDetails/{id}", method = RequestMethod.GET)
    public String getAllPersonalPosts(Model model, @PathVariable("id") int groupId){
        privatePostMap = iPersonalGroupService.getPrivatePostsByGroupID(groupId);
        List<Post> posts = (List<Post>) privatePostMap.get("privatePosts");
        model.addAttribute("privatePosts",posts);
        return Views.PERSONALGROUPS;
    }
}
