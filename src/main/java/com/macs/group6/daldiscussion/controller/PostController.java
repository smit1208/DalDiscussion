package com.macs.group6.daldiscussion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class PostController {
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
        return Views.VIEWPOST;
    }

}
