package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;

public interface IRegisterDAO {
    int create(User user);
    public boolean userExists(User user);
}
