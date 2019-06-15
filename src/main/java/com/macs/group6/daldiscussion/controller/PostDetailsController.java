package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.HomeDAO;
import com.macs.group6.daldiscussion.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/getPosts")
public class PostDetailsController {

    private HomeDAO homeDAO = new HomeDAO();
    private List<Comment> commentList = new ArrayList<>();

     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String viewPostDetails(Model model, @PathVariable("id") int post_id){

        commentList = homeDAO.getComments(post_id);
        model.addAttribute("comments",commentList);

        return Views.POSTDETAILS;
    }
}
