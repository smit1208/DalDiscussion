package com.macs.group6.daldiscussion.controller;

//Multi part file referenced from
//https://www.baeldung.com/spring-file-upload

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.service.AmazonClient;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.ISubscriptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller

public class PostController {
    private static final Logger logger = Logger.getLogger(PostController.class);
    private ISubscriptionService iSubscriptionService;
    private IPostService postService;
    private AmazonClient amazonClient;

    @Autowired
    public PostController(@Qualifier("PostService") IPostService iPostService, @Qualifier("SubscriptionService")ISubscriptionService iSubscriptionService){
        this.postService = iPostService;
        this.iSubscriptionService = iSubscriptionService;
        this.amazonClient = amazonClient;
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String postView(Model model, HttpSession session) {
        Map<String,Object> displaySubMap = new HashMap<>();
        int userID = (Integer)session.getAttribute("id");
        String name = (String) session.getAttribute("firstName");
        model.addAttribute("name",name);
        displaySubMap = iSubscriptionService.approvedSubscriptions(userID);
        List<Subscription> subscriptions = (List<Subscription>) displaySubMap.get("displayApprovedSubscriptions");
        model.addAttribute("approvedSubscription",subscriptions);
        return Views.VIEWPOST;
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(@RequestParam("postTitle") String postTitle,
                           @RequestParam("postDesc") String postDesc,
                           @RequestParam("category") Integer category,
                           @RequestParam("group") Integer group,
                           @RequestParam("image") List<MultipartFile> file, Model model, HttpSession session) {

        Post post = new Post();
        int user_id = (Integer) session.getAttribute("id");
        String imageMessage = "";

        if(postTitle!=null && postTitle.length()>0){

            post.setPost_title(postTitle);
        }
        if(postDesc!=null && postDesc.length()>0 ){

            post.setPost_description(postDesc);
        }
        if(category!=null && category>0){

            post.setCategory(category);
        }
        if(group!=null){

            post.setGroup(group);
        }

        List<String> fileNames = new ArrayList<String>();
        if (null != file && file.size() > 0)
        {
            for (MultipartFile multipartFile : file) {

                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
            }
            postService.createPostWithImage(post,file, user_id);

        }
        else{

            postService.create(post,user_id);
        }
        logger.info("Post added successfully");
        return "redirect:/home";
    }

}
