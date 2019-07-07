package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IRegisterDAO;
import com.macs.group6.daldiscussion.dao.RegisterDAO;
import com.macs.group6.daldiscussion.model.UserRegister;

public class RegisterService implements IRegisterService {

    IRegisterDAO registerDao = new RegisterDAO();
    @Override
    public void create(UserRegister userRegister) {
        registerDao.create(userRegister);
    }
}
