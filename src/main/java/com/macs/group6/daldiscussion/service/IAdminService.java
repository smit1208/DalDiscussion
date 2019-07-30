package com.macs.group6.daldiscussion.service;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.exceptions.DAOException;
import com.macs.group6.daldiscussion.model.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IAdminService {
    User admin() throws DAOException;
    List<Subscription> fetchAllSubscriptionRequests() throws DAOException;
    void approveSubscription(int subscription_id) throws DAOException;
    Map<String,Object> getPostsByMaxReports() throws DAOException;
}
