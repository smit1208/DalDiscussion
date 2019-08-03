package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.factory.DAOFactory;
import com.macs.group6.daldiscussion.factory.IDAOFactory;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Smit Saraiya
 */
@Service("SubscriptionService")
public class SubscriptionService implements ISubscriptionService{
    private IDAOFactory idaoFactory;

    public SubscriptionService(){
       idaoFactory = new DAOFactory();
    }

    @Override
    public List<SubscriptionGroup> getAllSubscriptions() throws DAOException {
        return idaoFactory.createSubscriptionDAO().getAllSubscription();
    }

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) throws DAOException {
        idaoFactory.createSubscriptionDAO().addSubscriptionRequest(user_id,group_id);
    }

    @Override
    public void addDefaultSubscriptionRequest(int user_id) throws DAOException {
        idaoFactory.createSubscriptionDAO().addDefaultSubscriptionRequest(user_id);
    }

    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) throws DAOException {
        return idaoFactory.createSubscriptionDAO().fetchSubscriptionByUserID(user_id);
    }

    @Override
    public Map<String,Object> approvedSubscriptions(int user_id) throws DAOException {
        return idaoFactory.createSubscriptionDAO().approvedSubscriptions(user_id);
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) throws DAOException {
        return idaoFactory.createSubscriptionDAO().fetchSubscriptionByID(subscription_id);
    }
}
