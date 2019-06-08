package com.macs.group6.daldiscussion.model;

public class comment {
    private int id;
    private String comment_description;
    private int commentUp;
    private int commentDown;

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
}
