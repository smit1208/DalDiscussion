package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;

import java.util.HashMap;
import java.util.Map;

public class CommentDAOMock implements ICommentDAO {
   private Map<String,Object> commentMap;
   private Post post;


    public CommentDAOMock(){
        commentMap = new HashMap<>();
        post = new Post();

    }



    @Override
    public Map<String, Object> getComments(int postId) {
        return commentMap;
    }

    @Override
    public Post getPostById(int postId) {
        return post;
    }

    @Override
    public void addComment(Comment comment, int post_id) {

    }
}
