package com.macs.group6.daldiscussion.dao;

public interface IDAOFactory {
    IHomeDAO getHomeDAO();
    ICommentDAO getCommentDAO();
    IReplyDAO getReplyDAO();
    IPostDAO getPostDAO();
}
