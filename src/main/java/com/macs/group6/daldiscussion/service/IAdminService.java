package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdminService {
    User admin();
    List<Subscription> fetchAllSubscriptionRequests();
    void approveSubscription(int subscription_id);
}
