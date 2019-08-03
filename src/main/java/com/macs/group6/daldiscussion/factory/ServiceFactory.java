package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.service.*;

/**
 * @author Smit Saraiya
 */
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
    public ICommentService createCommentService() {
        return new CommentService();
    }

    @Override
    public IReplyService createReplyService() {
        return new ReplyService();
    }

    @Override
    public IImageService createImageService() {
        return new ImageService();
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
