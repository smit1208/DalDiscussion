package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService implements IUserService{

    private UserDAO userDAO;
    private static UserService __instance;

    @Autowired
    public UserService(@Qualifier("UserDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public static UserService getInstance() {
        if (__instance == null) {
            __instance = new UserService(new UserDAO());
        }
        return __instance;
    }

    @Override
    public List<User> getUserByEmail(String email) {
        try {
            return userDAO.findByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int user_id) {
        try {
            return userDAO.findById(user_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserKarmaPoints(int karmaPoints, int postid) throws DAOException {
         userDAO.updateUserKarmaPoints(karmaPoints, postid);
    }

    public boolean updateUser(int id, String fname,String lname,String email, String password) {
        return UserDAO.getInstance().updateUser( id, fname, lname, email, password);
        }
}
