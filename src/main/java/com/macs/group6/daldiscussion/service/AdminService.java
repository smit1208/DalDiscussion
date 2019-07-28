package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("AdminService")
public class AdminService implements IAdminService {
    private IDAOFactory idaoFactory;

    public AdminService(){
        idaoFactory = new DAOFactory();
    }

    @Override
    public User admin() {
        return idaoFactory.createAdminDAO().getAdmin();
    }

    @Override
    public List<Subscription> fetchAllSubscriptionRequests() {
        return idaoFactory.createAdminDAO().fetchAllSubscriptionRequests();
    }

    @Override
    public void approveSubscription(int subscription_id) {
        idaoFactory.createAdminDAO().approveSubscription(subscription_id);
    }

    @Override
    public Map<String, Object> getPostsByMaxReports() {
        return idaoFactory.createAdminDAO().getPostsByMaxReports();
    }
}
