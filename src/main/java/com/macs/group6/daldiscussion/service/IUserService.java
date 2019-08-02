package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;

import java.util.List;

public interface IUserService {
    List<User> getUserByEmail(String email);
    User getUserById(int user_id);
    void updateUserKarmaPoints(int karmaPoints, int postid) throws DAOException;
}
