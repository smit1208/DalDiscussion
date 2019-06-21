package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.dao.impl.CommentDAO;
import com.macs.group6.daldiscussion.dao.impl.HomeDAO;
import com.macs.group6.daldiscussion.dao.impl.ReplyDAO;

public class DAOFactory implements IDAOFactory {

    private static DAOFactory daoFactory;

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    @Override
    public IHomeDAO getHomeDAO() {
        return HomeDAO.getInstance();
    }

    @Override
    public ICommentDAO getCommentDAO() {
        return CommentDAO.getInstance();
    }

    @Override
    public IReplyDAO getReplyDAO() {
        return ReplyDAO.getInstance();
    }
}
