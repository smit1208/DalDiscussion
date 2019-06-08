package com.macs.group6.daldiscussion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PostController {
    @RequestMapping("/addPost")
    public ModelAndView addPost() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("post");
        return mav;
    }
}
