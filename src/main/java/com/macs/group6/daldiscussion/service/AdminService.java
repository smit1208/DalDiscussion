package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IAdminDAO;
import com.macs.group6.daldiscussion.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminService implements IAdminService {
    private IAdminDAO iAdminDAO;

    @Autowired
    public AdminService(@Qualifier("AdminDAO") IAdminDAO iAdminDAO){
        this.iAdminDAO = iAdminDAO;
    }

    @Override
    public User admin() {
        return iAdminDAO.getAdmin();
    }
}
