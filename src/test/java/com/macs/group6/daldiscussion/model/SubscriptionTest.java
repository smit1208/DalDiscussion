package com.macs.group6.daldiscussion.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubscriptionTest {
    Subscription subscription = new Subscription();
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getGroupName() {
        subscription.setGroupName("Quality assurance");
        assertEquals("Quality assurance",subscription.getGroupName());
        assertNotEquals("Data Science",subscription.getGroupName());
    }

    @Test
    public void getId() {
        subscription.setId(1);
        assertEquals(1,subscription.getId());
        assertNotEquals(2,subscription.getId());
    }

    @Test
    public void getUser_id() {
        subscription.setUser_id(11);
        assertEquals(11,subscription.getUser_id());
        assertNotEquals(12,subscription.getUser_id());
    }

    @Test
    public void getGroup_id() {
        subscription.setId(12);
        assertEquals(12,subscription.getId());
        assertNotEquals(22,subscription.getId());
    }
}