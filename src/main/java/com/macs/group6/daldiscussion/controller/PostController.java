package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.PostService;
import com.macs.group6.daldiscussion.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class PostController {

    PostService postService = new PostServiceImpl();

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String postView() {
        return Views.VIEWPOST;
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(@RequestParam("postTitle") String postTitle,
                          @RequestParam("postDesc") String postDesc,
                          Map<String,Object> map) {
        map.put("postTitle",postTitle);
        map.put("postDesc",postDesc);
        Post post = new Post();
        post.setPost_title(postTitle);
        post.setPost_description(postDesc);
        postService.create(post);
        return Views.VIEWPOST;
    }

}
