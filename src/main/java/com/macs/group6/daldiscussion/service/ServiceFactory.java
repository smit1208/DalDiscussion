package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.ICommentDAO;
import com.macs.group6.daldiscussion.dao.IHomeDAO;
import com.macs.group6.daldiscussion.dao.IPostDAO;
import com.macs.group6.daldiscussion.dao.IReplyDAO;

public class ServiceFactory implements IServiceFactory {

    private static ServiceFactory serviceFactory;

    @Override
    public IHomeService getHomeService(IHomeDAO homeDAO) {
        return HomeService.getInstance(homeDAO);
    }

    @Override
    public IPostService getPostService(IPostDAO iPostDAO, ICommentDAO iCommentDAO, IReplyDAO iReplyDAO) {
        return PostService.getInstance(iPostDAO,iCommentDAO,iReplyDAO);
    }

    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }
}
