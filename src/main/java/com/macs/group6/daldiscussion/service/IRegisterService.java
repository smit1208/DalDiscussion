package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import org.springframework.stereotype.Service;

/**
 * @author Vivek Shah
 */

    @Service
    public interface IRegisterService {
        void create(User user) throws DAOException;
        boolean userExists(User userRegister) throws DAOException;
    }

