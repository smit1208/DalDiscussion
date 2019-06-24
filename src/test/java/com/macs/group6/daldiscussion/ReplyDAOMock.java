package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.IReplyDAO;
import com.macs.group6.daldiscussion.model.Reply;

import java.util.ArrayList;
import java.util.List;

public class ReplyDAOMock implements IReplyDAO {

    List<Reply> replyList;

    public ReplyDAOMock(){
        replyList = new ArrayList<>();
        replies();
    }

    private void replies(){
        Reply reply = new Reply();
        reply.setId(1);
        reply.setReply_description("Testing for replies");
        replyList.add(reply);
    }

    @Override
    public List<Reply> getReplies(int commentId) {
        return replyList;
    }

    @Override
    public void addReply(Reply reply, int comment_id) {

    }
}
