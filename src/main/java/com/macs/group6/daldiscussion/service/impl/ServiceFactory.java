package com.macs.group6.daldiscussion.service.impl;

import com.macs.group6.daldiscussion.service.IHomeService;
import com.macs.group6.daldiscussion.service.IPostService;
import com.macs.group6.daldiscussion.service.IServiceFactory;

public class ServiceFactory implements IServiceFactory {

    private static ServiceFactory serviceFactory;

    @Override
    public IHomeService getHomeService() {
        return HomeService.getInstance();
    }

    @Override
    public IPostService getPostService() {
        return PostService.getInstance();
    }

    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }
}
