package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public interface IReplyDAO {
    List<Reply> getReplies(int commentId);
    void addReply(Reply reply, int comment_id, int user_id);
}
