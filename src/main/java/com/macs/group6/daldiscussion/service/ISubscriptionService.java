package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ISubscriptionService {
    List<SubscriptionGroup> getAllSubscriptions() throws DAOException;
    void addSubscriptionRequest(int user_id, int group_id) throws DAOException;
    void addDefaultSubscriptionRequest(int user_id) throws DAOException;
    List<Subscription> fetchSubscriptionByUserID(int user_id) throws DAOException;
    Map<String,Object> approvedSubscriptions(int user_id) throws DAOException;
    Subscription fetchSubscriptionByID(int subscription_id) throws DAOException;
}
