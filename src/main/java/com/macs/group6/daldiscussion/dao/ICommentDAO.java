package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;

import java.util.List;

public interface ICommentDAO {
    List<Comment> getComments(int postId);
    Post getPostById(int postId);
    void addComment(Comment comment, int post_id);
}
