package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.UserRegister;
import com.macs.group6.daldiscussion.service.UserService;

public interface IRegisterDao {
    void create(UserRegister userRegister);
}
