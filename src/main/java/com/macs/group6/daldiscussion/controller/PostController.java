package com.macs.group6.daldiscussion.controller;

//Multi part file referenced from
//https://www.baeldung.com/spring-file-upload

import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.service.PostService;
import com.macs.group6.daldiscussion.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller

public class PostController {
    private static final Logger LOGGER = LogManager.getLogger(PostController.class);
    PostService postService = (PostService) ServiceFactory.getInstance().getPostService(
            (IPostDAO) DAOFactory.getInstance().getPostDAO(),
            (ICommentDAO) DAOFactory.getInstance().getCommentDAO(),
            (IReplyDAO) DAOFactory.getInstance().getReplyDAO()
    );

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
            postService.createPostWithImage(post,file);
            post.setFile(file);
        }else{
            postService.create(post);
        }

        return "redirect:/home";
    }

}
