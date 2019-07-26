package com.macs.group6.daldiscussion.model;

import java.util.List;

public class Comment {
    private int id;
    private String comment_description;
    private int commentUp;
    private int commentDown;
    private String CommentBy;
    private int post_id;
    private int user_id;
    private List<Reply> replies;

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_description() {
        return comment_description;
    }

    public void setComment_description(String comment_description) {
        this.comment_description = comment_description;
    }

    public int getCommentUp() {
        return commentUp;
    }

    public void setCommentUp(int commentUp) {
        this.commentUp = commentUp;
    }

    public int getCommentDown() {
        return commentDown;
    }

    public void setCommentDown(int commentDown) {
        this.commentDown = commentDown;
    }

    public String getCommentBy() {
        return CommentBy;
    }

    public void setCommentBy(String commentBy) {
        CommentBy = commentBy;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
