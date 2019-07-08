package com.macs.group6.daldiscussion;

import com.macs.group6.daldiscussion.model.SubscriptionGroup;
import com.macs.group6.daldiscussion.service.SubscriptionService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class SubscriptionServiceTest {

    private SubscriptionDAOMock subscriptionDAOMock;
    private SubscriptionService subscriptionService;

    @Before
    public void setUp() throws Exception {
        subscriptionDAOMock = new SubscriptionDAOMock();
        subscriptionService = new SubscriptionService(subscriptionDAOMock);
    }

    @Test
    public void getAllSubscriptions() {
        List<SubscriptionGroup> subscriptionGroupList = subscriptionDAOMock.getAllSubscription();

        for (int i = 0; i < subscriptionGroupList.size() ; i++) {

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
    }

    @Test
    public void approvedSubscriptions() {
    }

    @Test
    public void fetchSubscriptionByID() {
    }
}