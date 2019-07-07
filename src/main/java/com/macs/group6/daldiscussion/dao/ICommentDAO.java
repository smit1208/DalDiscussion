package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;

import java.util.Map;

public interface ICommentDAO {
    Map<String,Object> getComments(int postId);
    Post getPostById(int postId);
    void addComment(Comment comment, int post_id, int user_id);
}
