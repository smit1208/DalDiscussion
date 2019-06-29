package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.ISubscriptionDAO;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SubscriptionService")
public class SubscriptionService implements ISubscriptionService{
    private ISubscriptionDAO iSubscriptionDAO;
    @Autowired
    public SubscriptionService(@Qualifier("SubscriptionDAO")ISubscriptionDAO iSubscriptionDAO){
        this.iSubscriptionDAO = iSubscriptionDAO;
    }

    @Override
    public List<SubscriptionGroup> getAllSubscriptions() {
        return iSubscriptionDAO.getAllSubscription();
    }
}
