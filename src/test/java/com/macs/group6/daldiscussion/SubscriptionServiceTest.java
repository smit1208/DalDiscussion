package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.dao.ISubscriptionDAO;
import com.macs.group6.daldiscussion.model.Subscription;
import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class SubscriptionServiceTest {

    private ISubscriptionDAO subscriptionDAOMock;

    @Before
    public void setUp() throws Exception {
        subscriptionDAOMock = new SubscriptionDAOMock();
    }
    private List<SubscriptionGroup> subscriptionGroupList = new ArrayList<>();
    @Test
    public void getAllSubscriptions() {


        for (int i = 0; i < subscriptionGroupList.size() ; i++) {
            subscriptionGroupList = subscriptionDAOMock.getAllSubscription();
            assertEquals(1,subscriptionGroupList.get(i).getId());
            assertEquals("QA",subscriptionGroupList.get(i).getName());
            assertEquals(2,subscriptionGroupList.get(i).getMax_count());

        }
    }

    @Test
    public void addSubscriptionRequest() {
    }

    @Test
    public void fetchSubscriptionByUserID() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions = subscriptionDAOMock.fetchSubscriptionByUserID(1);
        assertEquals("Quality Assurance",subscriptions.get(0).getGroupName());
        assertNotNull(subscriptions);
    }

    @Test
    public void approvedSubscriptions() {
        Map<String, Object> subscriptionMap = new HashMap<>();
        subscriptionMap = subscriptionDAOMock.approvedSubscriptions(1);
        List<Subscription> subscriptionList = (List<Subscription>) subscriptionMap.get("approved");
        int groupId = subscriptionList.get(0).getGroup_id();
        assertEquals(1,groupId);
    }

    @Test
    public void fetchSubscriptionByID() {
        Subscription subscription = new Subscription();
        subscription = subscriptionDAOMock.fetchSubscriptionByID(2);
        assertEquals(12,subscription.getUser_id());
        assertNotNull(subscription);
    }
}