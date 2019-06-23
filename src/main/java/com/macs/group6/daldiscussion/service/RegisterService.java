package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IRegisterDao;
import com.macs.group6.daldiscussion.dao.RegisterDaoImpl;
import com.macs.group6.daldiscussion.model.UserRegister;

public class RegisterService implements IRegisterService {

    IRegisterDao registerDao = new RegisterDaoImpl();
    @Override
    public void create(UserRegister userRegister) {
        registerDao.create(userRegister);
    }
}
