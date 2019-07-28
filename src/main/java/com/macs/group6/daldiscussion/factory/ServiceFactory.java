package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.service.*;

public class ServiceFactory implements IServiceFactory {
    @Override
    public IHomeService createHomeService() {
        return new HomeService();
    }

    @Override
    public IAdminService createAdminService() {
        return new AdminService();
    }

    @Override
    public IPostService createPostService() {
        return new PostService();
    }

    @Override
    public ISubscriptionService createSubscriptionService() {
        return new SubscriptionService();
    }

    @Override
    public IPersonalGroupService createPersonalGroupService() {
        return new PersonalGroupService();
    }
}
