package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.service.*;

public interface IServiceFactory {
    IHomeService createHomeService();
    IAdminService createAdminService();
    IPostService createPostService();
    ISubscriptionService createSubscriptionService();
    IPersonalGroupService createPersonalGroupService();
}
