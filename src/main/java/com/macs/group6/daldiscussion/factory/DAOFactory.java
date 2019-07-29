package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.dao.*;

public class DAOFactory implements IDAOFactory {
    @Override
    public IHomeDAO createHomeDAO() {
        return new HomeDAO();
    }

    @Override
    public IAdminDAO createAdminDAO() {
        return new AdminDAO();
    }

    @Override
    public IReplyDAO createReplyDAO() {
        return new ReplyDAO();
    }

    @Override
    public IPostDAO createPostDAO() {
        return new PostDAO();
    }

    @Override
    public ISubscriptionDAO createSubscriptionDAO() {
        return new SubscriptionDAO();
    }

    @Override
    public IPersonalGroupDAO createPersonalGroupDAO() {
        return new PersonalGroupDAO();
    }

    @Override
    public ICommentDAO createCommentDAO() {
        return new CommentDAO();
    }

    @Override
    public IPostImageDAO createPostImageDAO() {
        return new PostImageDAO();
    }
}
