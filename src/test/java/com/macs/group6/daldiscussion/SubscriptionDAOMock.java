package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ISubscriptionDAO;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubscriptionDAOMock implements ISubscriptionDAO {
    List<SubscriptionGroup> subscriptions;

    public SubscriptionDAOMock() {
        subscriptions = new ArrayList<>();
        Subscriptions();
    }

    private void Subscriptions() {
        SubscriptionGroup subscription = new SubscriptionGroup();

        subscription.setId(1);
        subscription.setName("QA");
        subscription.setMax_count(2);
        subscriptions.add(subscription);
    }

    @Override
    public List<SubscriptionGroup> getAllSubscription() {
        return subscriptions;
    }

    @Override
    public void addSubscriptionRequest(int user_id, int group_id) {

    }

    @Override
    public List<Subscription> fetchSubscriptionByUserID(int user_id) {
        return null;
    }

    @Override
    public Map<String, Object> approvedSubscriptions(int user_id) {
        return null;
    }

    @Override
    public Subscription fetchSubscriptionByID(int subscription_id) {
        return null;
    }
}
