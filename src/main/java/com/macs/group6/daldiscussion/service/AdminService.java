package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.IAdminDAO;
import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Subscription> fetchAllSubscriptionRequests() {
        return iAdminDAO.fetchAllSubscriptionRequests();
    }

    @Override
    public void approveSubscription(int subscription_id) {
        iAdminDAO.approveSubscription(subscription_id);
    }
}
