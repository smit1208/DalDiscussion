package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import org.springframework.stereotype.Service;

    @Service
    public interface IRegisterService {
        void create(User user);
        boolean userExists(User userRegister);
    }

