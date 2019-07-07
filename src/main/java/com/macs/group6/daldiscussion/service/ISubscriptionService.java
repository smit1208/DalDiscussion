package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ISubscriptionService {
    List<SubscriptionGroup> getAllSubscriptions();
    void addSubscriptionRequest(int user_id, int group_id);
    List<Subscription> fetchSubscriptionByUserID(int user_id);
    Map<String,Object> approvedSubscriptions(int user_id);
    Subscription fetchSubscriptionByID(int subscription_id);
}