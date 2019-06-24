package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;

public interface IServiceFactory {
    IHomeService getHomeService(IHomeDAO iHomeDAO);
    IPostService getPostService(IPostDAO iPostDAO, ICommentDAO iCommentDAO, IReplyDAO iReplyDAO);
}
