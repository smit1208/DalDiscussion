package com.macs.group6.daldiscussion.service;


import com.macs.group6.daldiscussion.dao.*;

public class UserServiceObserver implements  IUserService, IObserver{
    private static IObserver iUserService;

    private UserDAO userDAO;
    private static PostService postUpdates = (PostService) ServiceFactory.getInstance().getPostService(
            (IPostDAO) DAOFactory.getInstance().getPostDAO(),
            (ICommentDAO) DAOFactory.getInstance().getCommentDAO(),
            (IReplyDAO) DAOFactory.getInstance().getReplyDAO()
    );
    public static IObserver getInstance(){
        if(iUserService == null){
            iUserService = new UserServiceObserver();
        }
        return iUserService;
    }
    private UserServiceObserver(){
        postUpdates.attach(this);
    }
    public boolean isUserPresent(String email){
        return false;
    }

    @Override
    public void update(int karmaPoints, int postID) {

        System.out.println("karma "+karmaPoints);
        System.out.println("postID" + postID);
        UserDAO.getInstance().updateUserKarmaPoints(karmaPoints, postID);
    }


}
