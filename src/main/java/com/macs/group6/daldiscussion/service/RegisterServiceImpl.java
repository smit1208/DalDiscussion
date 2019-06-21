package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.RegisterDao;
import com.macs.group6.daldiscussion.dao.RegisterDaoImpl;
import com.macs.group6.daldiscussion.model.UserRegister;

public class RegisterServiceImpl implements RegisterService{

    RegisterDao registerDao = new RegisterDaoImpl();
    @Override
    public void create(UserRegister userRegister) {
        registerDao.create(userRegister);
    }
}
