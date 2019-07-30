package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public interface IReplyDAO {
    List<Reply> getReplies(int commentId) throws DAOException;
    void addReply(Reply reply, int comment_id, int user_id, String name) throws DAOException;
}
