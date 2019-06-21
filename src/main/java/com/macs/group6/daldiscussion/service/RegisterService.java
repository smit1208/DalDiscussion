package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.UserRegister;
import org.springframework.stereotype.Service;

    @Service("RegisterService")
    public interface RegisterService{
        void create(UserRegister user);
    }

