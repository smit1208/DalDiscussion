package com.macs.group6.daldiscussion.model;

public class Reply {
    private int id;
    private String reply_description;
    private String replyBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReply_description() {
        return reply_description;
    }

    public void setReply_description(String reply_description) {
        this.reply_description = reply_description;
    }

    public String getReplyBy() {
        return replyBy;
    }

    public void setReplyBy(String replyBy) {
        this.replyBy = replyBy;
    }
}
