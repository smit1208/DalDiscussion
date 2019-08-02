package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Reply;

import java.util.List;

public class ReplyService implements IReplyService {

    private IDAOFactory idaoFactory;

    public ReplyService(){
        idaoFactory = new DAOFactory();
    }
    @Override
    public List<Reply> getReplies(int commentId) throws DAOException {
        return idaoFactory.createReplyDAO().getReplies(commentId);
    }

    @Override
    public void addReply(Reply reply, int comment_id, int user_id, String name) throws DAOException {
        idaoFactory.createReplyDAO().addReply(reply,comment_id,user_id,name);

    }
}
