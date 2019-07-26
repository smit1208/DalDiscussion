package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.PostImage;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.IPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostDetailsController {
    private IPostService iPostService;
    private static final Logger logger = Logger.getLogger(PostDetailsController.class);

    public PostDetailsController(@Qualifier("PostService") IPostService iPostService) {
        this.iPostService = iPostService;
    }
    @RequestMapping(value = "/getPosts/{id}", method = RequestMethod.GET)
    public String viewPostDetails(ModelMap model, @PathVariable("id") int post_id) {
        Map<String, Object> commentMap = new HashMap<>();
        Post post = new Post();
        List<PostImage> images = new ArrayList<>();
        post = iPostService.getPostById(post_id);
        images = iPostService.getImageByPostId(post_id);
        commentMap = iPostService.getComments(post_id);
        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = (List<Comment>) commentMap.get("commentList");

        for (Comment comment : commentList) {
            replyList = iPostService.getReplies(comment.getId());
            comment.setReplies(replyList);
        }
        model.addAttribute("images",iPostService.getImageByPostId(post_id));
        model.addAttribute("comments", commentList);
        model.addAttribute("post", post);
        logger.info("Post details successfully fetched");
        return Views.POSTDETAILS;
    }


    @PostMapping("/getPosts/{id}")
    public String addComment(@RequestParam("comment") String comment, ModelMap model, @PathVariable("id") int post_id, HttpSession session) {
        Comment c = new Comment();
        Post post = new Post();
        Map<String, Object> commentMap = new HashMap<>();

        int user_id = (Integer) session.getAttribute("id");
        c.setComment_description(comment);
        iPostService.addComment(c, post_id,user_id);
        commentMap = iPostService.getComments(post_id);
        post = iPostService.getPostById(post_id);
        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = (List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentList.size(); i++) {
            replyList = iPostService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        logger.info("Comment successfully added");
        return Views.POSTDETAILS;
    }

    @PostMapping("/getPosts/{id}/{c_id}")
    public String addReply(@RequestParam("reply") String reply, ModelMap model, @PathVariable("id") int post_id, @PathVariable("c_id") int comment_id,
                           HttpSession session) {
        Map<String, Object> commentMap = new HashMap<>();
        Reply replies = new Reply();
        Post post = new Post();
        int user_id = (Integer) session.getAttribute("id");
        post = iPostService.getPostById(post_id);
        commentMap = iPostService.getComments(post_id);
        replies.setReply_description(reply);

        /* Modified by @Sharon
        for update Post Modification Date*/

        iPostService.addReply(replies, comment_id,user_id, post_id);
        /*******************************************************/

        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = (List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentList.size(); i++) {
            replyList = iPostService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);

        logger.info("Reply added successfully");

        return "redirect:/getPosts/{id}";

    }

}
