package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.service.*;

/**
 * @author Smit Saraiya
 */
public interface IServiceFactory {
    IHomeService createHomeService();
    IAdminService createAdminService();
    IPostService createPostService();
    ICommentService createCommentService();
    IReplyService createReplyService();
    IImageService createImageService();
    ISubscriptionService createSubscriptionService();
    IPersonalGroupService createPersonalGroupService();
}
