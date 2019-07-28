package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ISubscriptionDAO;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionDAOMock implements ISubscriptionDAO {
    List<SubscriptionGroup> subscriptions;
    List<Subscription> subscriptionList;
    Map<String,Object> subscriptionMap;

    public SubscriptionDAOMock() {
        subscriptions = new ArrayList<>();
        subscriptionList = new ArrayList<>();
        subscriptionMap = new HashMap<>();
        Subscriptions();
    }
    Subscription subscription1;
    private void Subscriptions() {
        SubscriptionGroup subscription = new SubscriptionGroup();

        subscription.setId(1);
        subscription.setName("QA");
        subscription.setMax_count(2);
        subscriptions.add(subscription);

        subscription1 = new Subscription();
        subscription1.setGroupName("Quality Assurance");
        subscription1.setGroup_id(1);
        subscription1.setUser_id(12);
        subscription1.setId(2);
        subscriptionList.add(subscription1);

        subscriptionMap.put("approved",subscriptionList);
    }

    @Override
    public List<SubscriptionGroup> getAllSubscription() {
        return subscriptions;
    }

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) {

    }

    @Override
    public void addDefaultSubscriptionRequest(int user_id) {

    }

    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) {
        return subscriptionList;
    }

    @Override
    public Map<String, Object> approvedSubscriptions(int user_id) {
        return subscriptionMap;
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) {
        return subscription1;
    }
}
