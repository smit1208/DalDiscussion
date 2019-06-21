package com.macs.group6.daldiscussion.dao;

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
