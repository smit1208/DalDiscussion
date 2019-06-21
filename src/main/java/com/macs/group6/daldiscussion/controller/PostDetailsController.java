package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.impl.PostService;
import com.macs.group6.daldiscussion.service.impl.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostDetailsController {
    PostService postService = (PostService) ServiceFactory.getInstance().getPostService();
    List<Comment> commentList = new ArrayList<>();
    Post post = new Post();

    @RequestMapping(value = "/getPosts/{id}", method = RequestMethod.GET)
    public String viewPostDetails(Model model, @PathVariable("id") int post_id) {
        post = postService.getPostById(post_id);

        commentList = postService.getComments(post_id);
        List<Reply> replyList = null;

        for (int i = 0; i < commentList.size(); i++) {
            replyList = postService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }
        model.addAttribute("comments", commentList);
        model.addAttribute("post", post);

        return Views.POSTDETAILS;
    }


    @PostMapping("/getPosts/{id}")
    public String addComment(@RequestParam("comment") String comment, ModelMap model,@PathVariable("id") int post_id) {
        Comment c = new Comment();
        c.setComment_description(comment);
        postService.addComment(c,post_id);
        commentList = postService.getComments(post_id);
        post = postService.getPostById(post_id);

        List<Reply> replyList = null;

        for (int i = 0; i < commentList.size(); i++) {
            replyList = postService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);

        }

        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        return Views.POSTDETAILS;
    }
    @PostMapping("/getPosts/{id}/{c_id}")
    public String addReply(@RequestParam("reply") String reply,  ModelMap model,@PathVariable("id") int post_id, @PathVariable("c_id") int comment_id) {
        Reply replies = new Reply();
        replies.setReply_description(reply);

        postService.addReply(replies,comment_id);
        commentList = postService.getComments(post_id);
        post = postService.getPostById(post_id);

        List<Reply> replyList = null;

        for (int i = 0; i < commentList.size(); i++) {
            replyList = postService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }

        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);

        return "redirect:/getPosts/{id}";

    }

}
