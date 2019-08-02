package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;

import java.util.List;
import java.util.Map;

/**
 * @author Smit Saraiya
 */
public interface ISubscriptionDAO {
    List<SubscriptionGroup> getAllSubscription() throws DAOException;
    void addSubscriptionRequest(int user_id, int group_id) throws DAOException;
    void addDefaultSubscriptionRequest(int user_id) throws DAOException;
    List<Subscription> fetchSubscriptionByUserID(int user_id) throws DAOException;
    Map<String,Object> approvedSubscriptions(int user_id) throws DAOException;
    Subscription fetchSubscriptionByID(int subscription_id) throws DAOException;
}
