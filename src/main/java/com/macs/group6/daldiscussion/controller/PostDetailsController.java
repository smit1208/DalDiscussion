package com.macs.group6.daldiscussion.controller;

import com.macs.group6.daldiscussion.dao.DAOFactory;
import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;
import com.macs.group6.daldiscussion.service.PostService;
import com.macs.group6.daldiscussion.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PostDetailsController {
    private static final Logger LOGGER = LogManager.getLogger(PostDetailsController.class);
    PostService postService = (PostService) ServiceFactory.getInstance().getPostService(
            (IPostDAO) DAOFactory.getInstance().getPostDAO(),
            (ICommentDAO) DAOFactory.getInstance().getCommentDAO(),
            (IReplyDAO) DAOFactory.getInstance().getReplyDAO()
    );
    Map<String,Object> commentMap = new HashMap<>();
    Post post = new Post();

    @RequestMapping(value = "/getPosts/{id}", method = RequestMethod.GET)
    public String viewPostDetails(ModelMap model, @PathVariable("id") int post_id) {
        post = postService.getPostById(post_id);

        commentMap = postService.getComments(post_id);
        List<Reply> replyList = new ArrayList<>();
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (Comment comment : commentList)
        {
            replyList = postService.getReplies(comment.getId());
            comment.setReplies(replyList);
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
        post = postService.getPostById(post_id);
        commentMap = postService.getComments(post_id);
        replies.setReply_description(reply);
        postService.addReply(replies,comment_id);
        List<Reply> replyList = new ArrayList<>() ;
        List<Comment> commentList = ( List<Comment>) commentMap.get("commentList");

        for (int i = 0; i < commentList.size(); i++) {
            replyList = postService.getReplies(commentList.get(i).getId());
            commentList.get(i).setReplies(replyList);
        }


        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);

        return "redirect:/getPosts/{id}";

    }

}
