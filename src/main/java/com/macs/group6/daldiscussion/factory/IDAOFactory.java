package com.macs.group6.daldiscussion.factory;

import com.macs.group6.daldiscussion.dao.*;

/**
 * @author Smit Saraiya
 */
public interface IDAOFactory {
    IHomeDAO createHomeDAO();
    IAdminDAO createAdminDAO();
    IReplyDAO createReplyDAO();
    IPostDAO createPostDAO();
    ISubscriptionDAO createSubscriptionDAO();
    IPersonalGroupDAO createPersonalGroupDAO();
    ICommentDAO createCommentDAO();
    IPostImageDAO createPostImageDAO();
}
