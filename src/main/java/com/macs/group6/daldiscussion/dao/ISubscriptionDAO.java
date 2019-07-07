package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;

import java.util.List;
import java.util.Map;

public interface ISubscriptionDAO {
    List<SubscriptionGroup> getAllSubscription();
    void addSubscriptionRequest(int user_id, int group_id);
    List<Subscription> fetchSubscriptionByUserID(int user_id);
    Map<String,Object> approvedSubscriptions(int user_id);
    Subscription fetchSubscriptionByID(int subscription_id);
}
