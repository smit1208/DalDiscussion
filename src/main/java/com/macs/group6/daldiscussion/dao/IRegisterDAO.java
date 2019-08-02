package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
/**
 * @author Vivek Shah
 */

public interface IRegisterDAO {
    int create(User user) throws DAOException;
    public boolean userExists(User user) throws DAOException;
}
