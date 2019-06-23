package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public class ReplyDAOMock implements IReplyDAO {



    @Override
    public List<Reply> getReplies(int commentId) {
        return null;
    }

    @Override
    public void addReply(Reply reply, int comment_id) {

    }
}
