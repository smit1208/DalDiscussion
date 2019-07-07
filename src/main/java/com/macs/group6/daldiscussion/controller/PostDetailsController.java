package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.IPostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostDetailsController {
    private IPostService iPostService;
    private static final Logger LOGGER = LogManager.getLogger(PostDetailsController.class);

    public PostDetailsController(@Qualifier("PostService")IPostService iPostService){
        this.iPostService = iPostService;
    }
    Map<String,Object> commentMap = new HashMap<>();
    Post post = new Post();

    @RequestMapping(value = "/getPosts/{id}", method = RequestMethod.GET)
    public String viewPostDetails(ModelMap model, @PathVariable("id") int post_id) {
        post = iPostService.getPostById(post_id);

        commentMap = iPostService.getComments(post_id);
        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (Comment comment : commentList)
        {
            replyList = iPostService.getReplies(comment.getId());
            comment.setReplies(replyList);
        }


        model.addAttribute("comments", commentList);
        model.addAttribute("post", post);
        LOGGER.info("Post details successfully fetched");
        return Views.POSTDETAILS;
    }


    @PostMapping("/getPosts/{id}")
    public String addComment(@RequestParam("comment") String comment, ModelMap model, @PathVariable("id") int post_id) {
        Comment c = new Comment();
        c.setComment_description(comment);
        iPostService.addComment(c,post_id);
        commentMap = iPostService.getComments(post_id);
        post = iPostService.getPostById(post_id);

        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentList.size(); i++) {
            replyList = iPostService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }

        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        LOGGER.info("Comment successfully added");
        return Views.POSTDETAILS;
    }
    @PostMapping("/getPosts/{id}/{c_id}")
    public String addReply(@RequestParam("reply") String reply,  ModelMap model,@PathVariable("id") int post_id, @PathVariable("c_id") int comment_id) {
        Reply replies = new Reply();
        post = iPostService.getPostById(post_id);
        commentMap = iPostService.getComments(post_id);
        replies.setReply_description(reply);
        iPostService.addReply(replies,comment_id);
        List<Reply> replyList = new ArrayList<>() ;
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentList.size(); i++) {
            replyList = iPostService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);

        LOGGER.info("Reply added successfully");

        return "redirect:/getPosts/{id}";

    }

}
