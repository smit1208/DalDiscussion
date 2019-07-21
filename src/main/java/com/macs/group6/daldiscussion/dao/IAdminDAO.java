package com.macs.group6.daldiscussion.dao;

import com.macs.group6.daldiscussion.entities.User;
import com.macs.group6.daldiscussion.model.Subscription;

import java.util.List;
import java.util.Map;

public interface IAdminDAO {
  User getAdmin();
  List<Subscription> fetchAllSubscriptionRequests();
  void approveSubscription(int subscription_id);
  Map<String,Object> getPostsByMaxReports();
}
