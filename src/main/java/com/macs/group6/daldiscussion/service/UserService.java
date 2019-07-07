package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.UserDAO;
import com.macs.group6.daldiscussion.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService implements IUserService{

    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("UserDAO") UserDAO userDAO){
        this.userDAO = userDAO;
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
}
