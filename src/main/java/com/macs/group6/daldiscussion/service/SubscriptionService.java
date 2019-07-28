package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("SubscriptionService")
public class SubscriptionService implements ISubscriptionService{
    private IDAOFactory idaoFactory;

    public SubscriptionService(){
       idaoFactory = new DAOFactory();
    }

    @Override
    public List<SubscriptionGroup> getAllSubscriptions() {
        return idaoFactory.createSubscriptionDAO().getAllSubscription();
    }

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) {
        idaoFactory.createSubscriptionDAO().addSubscriptionRequest(user_id,group_id);
    }

    @Override
    public void addDefaultSubscriptionRequest(int user_id) {
        idaoFactory.createSubscriptionDAO().addDefaultSubscriptionRequest(user_id);
    }

    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) {
        return idaoFactory.createSubscriptionDAO().fetchSubscriptionByUserID(user_id);
    }

    @Override
    public Map<String,Object> approvedSubscriptions(int user_id) {
        return idaoFactory.createSubscriptionDAO().approvedSubscriptions(user_id);
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) {
        return idaoFactory.createSubscriptionDAO().fetchSubscriptionByID(subscription_id);
    }
}
