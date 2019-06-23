package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.PostService;
import com.macs.group6.daldiscussion.service.ServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostDetailsController {
    PostService postService = (PostService) ServiceFactory.getInstance().getPostService();
    Map<String,Object> commentMap = new HashMap<>();
    Post post = new Post();

    @RequestMapping(value = "/getPosts/{id}", method = RequestMethod.GET)
    public String viewPostDetails(ModelMap model, @PathVariable("id") int post_id) {
        post = postService.getPostById(post_id);

        commentMap = postService.getComments(post_id);
        List<Reply> replyList = null;
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

            for (int i = 0; i < commentMap.size(); i++) {
                commentList.get(i).setReplies(replyList);
                replyList = postService.getReplies(commentList.get(i).getId());
            }
        for (int i = 0; i < commentMap.size(); i++) {
            commentList.get(i).setReplies(replyList);
            replyList = postService.getReplies(commentList.get(i).getId());
        }
        model.addAttribute("comments", commentList);
        model.addAttribute("post", post);

        return Views.POSTDETAILS;
    }


    @PostMapping("/getPosts/{id}")
    public String addComment(@RequestParam("comment") String comment, ModelMap model, @PathVariable("id") int post_id) {
        Comment c = new Comment();
        c.setComment_description(comment);
        postService.addComment(c,post_id);
        commentMap = postService.getComments(post_id);
        post = postService.getPostById(post_id);

        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");
        for (int i = 0; i < commentMap.size(); i++) {
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
        post = postService.getPostById(post_id);
        commentMap = postService.getComments(post_id);

        replies.setReply_description(reply);

        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentMap.size(); i++) {
            replyList = postService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }

        postService.addReply(replies,comment_id);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);

        return "redirect:/getPosts/{id}";

    }

}
