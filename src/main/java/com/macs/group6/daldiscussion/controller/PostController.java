package com.macs.group6.daldiscussion.controller;

//Multi part file referenced from
//https://www.baeldung.com/spring-file-upload
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.impl.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller

public class PostController {
    private static final Logger LOGGER = LogManager.getLogger(PostController.class);
    IPostService IPostService = new PostService();

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String postView() {
        return Views.VIEWPOST;
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(@RequestParam("postTitle") String postTitle,
                          @RequestParam("postDesc") String postDesc,
                          @RequestParam("category") Integer category,
                          @RequestParam("group") String group,
                          @RequestParam("image") MultipartFile file) {

        Post post = new Post();
        if(postTitle!=null && postTitle.length()>0){
            post.setPost_title(postTitle);
        }
        if(postDesc!=null && postDesc.length()>0 ){
            post.setPost_description(postDesc);
        }else{
            LOGGER.info("postDesc is empty");
        }
        if(category!=null && category>0){
            post.setCategory(category);
        }
        if(group!=null && group.length()>0){
            post.setGroup(group);
        }
        LOGGER.info(post.getPost_title()+","+post.getPost_description()+","+post.getCategory()+","+post.getGroup());
        if (!file.isEmpty()) {
            IPostService.createPostWithImage(post,file);
            post.setFile(file);
        }else{
            IPostService.create(post);
        }

        return "redirect:/home";
    }

}
