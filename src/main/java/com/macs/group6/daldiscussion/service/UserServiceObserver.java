package com.macs.group6.daldiscussion.service;
import com.macs.group6.daldiscussion.dao.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceObserver implements IObserver{
    private static IObserver iUserServiceObserver;

    private UserDAO userDAO;
    @Autowired
    private static ISubject postUpdates ;

    public static IObserver getInstance(ISubject ipostService){
        if(iUserServiceObserver == null){
            iUserServiceObserver = new UserServiceObserver(ipostService);
        }
        return iUserServiceObserver;
    }

    private UserServiceObserver(ISubject ipostService){
        this.postUpdates = ipostService;
        if(postUpdates!=null){
            postUpdates.attach(this);
        }
        else {
            System.out.println("No post service");
        }
    }

    public boolean isUserPresent(String email){
        return false;
    }

    @Override
    public void update(int karmaPoints, int userID) {
        UserDAO.getInstance().updateUserKarmaPoints(karmaPoints, userID);
    }
}
