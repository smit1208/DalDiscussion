package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Comment;
import com.macs.group6.daldiscussion.model.Post;
import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public interface IPostService {
    List<Comment> getComments(int postId);
    List<Reply> getReplies(int commentId);
    Post getPostById(int postId);
    void addComment(Comment c, int post_id);
    void addReply(Reply reply,int comment_id);
}
