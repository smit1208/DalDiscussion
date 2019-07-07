package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.dao.ISubscriptionDAO;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) {
        iSubscriptionDAO.addSubscriptionRequest(user_id,group_id);
    }

    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) {
        return iSubscriptionDAO.fetchSubscriptionByUserID(user_id);
    }

    @Override
    public Map<String,Object> approvedSubscriptions(int user_id) {
        return iSubscriptionDAO.approvedSubscriptions(user_id);
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) {
        return iSubscriptionDAO.fetchSubscriptionByID(subscription_id);
    }
}
